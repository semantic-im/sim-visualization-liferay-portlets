package ro.utcluj.larkc.platformMetrics.shared;

import com.google.gwt.core.client.JavaScriptObject;


public class MetricEntry extends JavaScriptObject {
	
	protected MetricEntry() {}

	// JSNI methods to get RRD Metrics

	public final native String getTimeStamp() /*-{ return this.ts + ''}-*/; 
	public final native double getDateTime() /*-{ return this.datetime; }-*/;
	public final native double getSysOpenFileDescrCnt() /*-{ return this.sysOpenFileDescrCnt; }-*/;
	public final native double getWaitCPUTime() /*-{ return this.waitCPUTime; }-*/;
	public final native double getUserCPULoad() /*-{ return this.userCPULoad; }-*/;
	public final native double getTotalSysUsedMemory() /*-{ return this.totalSysUsedMemory; }-*/;
	public final native double getIdleCPUTime() /*-{ return this.idleCPUTime; }-*/;
	public final native double getIORead() /*-{ return this.iORead; }-*/;
	public final native double getSwapIn() /*-{ return this.swapIn; }-*/;
	public final native double getWaitCPULoad() /*-{ return this.waitCPULoad; }-*/;
	public final native double getTotalSysFreeMemory() /*-{ return this.totalSysFreeMemory; }-*/;
	public final native double getUserCPUTime() /*-{ return this.userCPUTime; }-*/;
	public final native double getSysLoadAverage() /*-{ return this.sysLoadAverage; }-*/;
	public final native double getSwapOut() /*-{ return this.swapOut; }-*/;
	public final native double getIOWrite() /*-{ return this.iOWrite; }-*/;
	public final native double getIdleCPULoad() /*-{ return this.idleCPULoad; }-*/;
	public final native double getTotalSysUsedSwap() /*-{ return this.totalSysUsedSwap; }-*/;
	public final native double getIrqCPUTime() /*-{ return this.irqCPUTime; }-*/;
	public final native double getSystemCPUTime() /*-{ return this.systemCPUTime; }-*/;
	public final native double getSystemCPULoad() /*-{ return this.systemCPULoad; }-*/;
	public final native double getIrqCPULoad() /*-{ return this.irqCPULoad; }-*/;

}
