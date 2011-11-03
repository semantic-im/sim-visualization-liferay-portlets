package ro.utcluj.larkc.metrics.client;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.Anchor;

public class AnchorCell extends AbstractCell<Anchor> {
	@Override
	public void render(Context context, Anchor h, SafeHtmlBuilder sb) {
	    sb.append(SafeHtmlUtils.fromTrustedString(h.toString()));
	}
	}