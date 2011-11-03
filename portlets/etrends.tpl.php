
<?php 
//drupal_add_js(drupal_get_path('module', 'etrends') .'/js/et_gsearch.js');
drupal_set_html_head('<script type="text/javascript" src="https://www.google.com/jsapi?key=ABQIAAAANxW39AHoir6OhnPr861iqhSX7biTWhVxgXtiQwGK_LMjlJKI1BRWZYMdKGsmk0RRMJBoW_O7iXVWqA"></script>');
drupal_add_js(drupal_get_path('module', 'etrends') .'/js/et_gsearch.js', 'theme');
drupal_add_js('
google.load("search", "1");

    function OnLoad() {
      // Create a search control
      var searchControl = new google.search.SearchControl();

      searchControl.addSearcher(new google.search.WebSearch());
      searchControl.addSearcher(new google.search.VideoSearch());
      searchControl.addSearcher(new google.search.BlogSearch());

	
      // Execute an inital search
      searchControl.execute("Traian Basescu, Mircea Geoana");
    
	}
    google.setOnLoadCallback(OnLoad);
	
	','inline'); 
print drupal_get_form('etrends_search_form');
$key = "ABQIAAAANxW39AHoir6OhnPr861iqhSX7biTWhVxgXtiQwGK_LMjlJKI1BRWZYMdKGsmk0RRMJBoW_O7iXVWqA";
$userip = $_SERVER['REMOTE_ADDR'];

$q = "Paris%20Hilton";

if(isset($_GET['concepts']))
{
  $concepts = Array();
  $keywords = explode(",", $_GET['concepts']);
  //count all chart values in order to estimate value precentage
  $cvalcount = 1;
  //max data size (computed from search results)
  $chds = 0;
  foreach($keywords as $item){
    
	$item = urlencode(trim($item,' `~!@#$%^&*()+=\{}[]<>,.?:;"'));
	$url = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&"
    . "q=".$item."&num=1"."&key=".$key."&userip=".$userip;
	$ch = curl_init();
	curl_setopt($ch, CURLOPT_URL, $url);
	curl_setopt($ch, CURLOPT_HEADER, false);
	curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
	curl_setopt($ch, CURLOPT_REFERER, 'http://localhost/visual');
	$body = curl_exec($ch);
	curl_close($ch);

	// now, process the JSON string
	$json = json_decode($body);

	//print $body;
	//print_r($response);
	
	$concepts[$item]['estimatedResultCount']=isset($json->responseData->cursor->estimatedResultCount) ? $json->responseData->cursor->estimatedResultCount : 0;
	//count values
	$cvalcount += $concepts[$item]['estimatedResultCount'];
	
	//max data size
    $chds = $concepts[$item]['estimatedResultCount'] > $chds ? $concepts[$item]['estimatedResultCount'] : $chds;
	
	//drupal_set_message('<pre>'.check_plain(print_r($json,true)).'</pre>');

  }

}




//print_r($form_state['values']);
//print $response ."sss";
//print_r($json);

?>
<div id="searchcontrol1">
<?php if(isset($concepts)): ?>
<h3>eTrends results:</h3>
<?php
//chart data values
$chd = Array();
$chdl = Array();
$chl = Array();

foreach($concepts as $item => &$value)
{
  //data values (number of results)
  $chd[] = $value['estimatedResultCount'];
 
  //chart labels (searched concepts)
  $chl[] = $item;
  
  
  //compute data percents
  $percent = round( ( $value['estimatedResultCount'] / $cvalcount ) * 100.0, 2);
  $value['estimatedPercent'] = $percent;//urlencode($percent."% - ".$value);
  //chart legend labels
  $chdl[] = $value['estimatedPercent']."% - ".$item;
  
  // print_r($concepts );
  print "<b>".urldecode($item).":</b> {$value['estimatedResultCount']} search results <br>";
}

//calculate the percentages of the chart values
foreach($concepts as $item => $value)
{
  
  
}
  $chart_labels = implode("|",$chl);
  $chart_legend_labels = implode("|",$chdl);
  $chds = "&chds=0,".$chds;
  //chart data
  $chd = "&chd=t:".implode(",",$chd);
  //labels
  $chl = "&chl=".$chart_labels;//chart labels
  $chdl = "&chdl=".$chart_legend_labels;//legend labels
  
  $chart_url = "http://chart.apis.google.com/chart?chs=640x425&cht=p&chco=FF7A00".
   $chds.
   $chd.
   $chdl.
   $chl."&chdlp=t&chtt=Web+dominance+by+eTrends.ro";

  
?>
<img src="<?php print $chart_url; ?>" alt="<?php print $_GET['concepts']; ?>">

<?php  endif; ?>

</div>
