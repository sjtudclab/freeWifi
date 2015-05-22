package cn.edu.sjtu.dclab.freewifi.tool;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.util.List;

/**
 * Created by Yang on 2015/5/22.
 */
public class WifiAdmin {
    private final String WIFITAG = "TEST";
    private WifiManager wifiManager;
    private WifiInfo wifiInfo;
    private List<ScanResult> wifiList;
    private List<WifiConfiguration> wifiCfg;
    private WifiManager.WifiLock wifiLock;

    public WifiAdmin(Context context) {
        wifiManager = (WifiManager) context.getSystemService(context.WIFI_SERVICE);
        wifiInfo = wifiManager.getConnectionInfo();
    }

    public void openWifi() {
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }
    }

    public void wifiScan() {
        wifiManager.startScan();
        wifiList = wifiManager.getScanResults();
        wifiCfg = wifiManager.getConfiguredNetworks();
    }

    public List<ScanResult> getWifiList() {
        return wifiList;
    }

    public List<WifiConfiguration> getWifiCfg() {
        return wifiCfg;
    }

    public void connectionCfg(int index) {
        if (index > wifiCfg.size()) {
            return;
        }
        wifiManager.enableNetwork(wifiCfg.get(index).networkId, true);
    }

    public void closeWifi() {
        if (wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(false);
        }
    }

    public int getWifiState() {
        return wifiManager.getWifiState();
    }

    public void createWifiLock() {
        wifiLock = wifiManager.createWifiLock(WIFITAG);
    }

    public void acquireWifiLock() {
        wifiLock.acquire();
    }

    public void releaseWifiLock() {
        if (wifiLock.isHeld()) {
            wifiLock.release();
        }
    }

    public String getMacAddress() {
        return (wifiInfo == null) ? "NULL" : wifiInfo.getMacAddress();
    }

    public int getIPAddress() {
        return (wifiInfo == null) ? 0 : wifiInfo.getIpAddress();
    }

    public String getSSID() {
        return (wifiInfo == null) ? "NULL" : wifiInfo.getBSSID();
    }

    public int getNetworkId() {
        return (wifiInfo == null) ? 0 : wifiInfo.getNetworkId();
    }

    public void addConnection(WifiConfiguration cfg) {
        int netId = wifiManager.addNetwork(cfg);
        wifiManager.enableNetwork(netId, true);
    }

    public void disconnect(int netId) {
        wifiManager.disableNetwork(netId);
        wifiManager.disconnect();
    }

    public StringBuffer lookUpScan() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < wifiList.size(); i++) {
            sb.append("Index_" + new Integer(i + 1).toString() + ":");
            sb.append((wifiList.get(i)).toString()).append("\n");
        }
        return sb;
    }
}
