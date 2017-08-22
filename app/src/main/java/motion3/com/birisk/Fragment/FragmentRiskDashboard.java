package motion3.com.birisk.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;

import motion3.com.birisk.MainActivity;
import motion3.com.birisk.R;

/**
 * Created by Semmy
 * mr.shanky08@gmail.com on 6/22/17.
 *
 * @copyright 2017
 */

public class FragmentRiskDashboard extends Fragment {
    private View view;
//    private String url = "http://www.pngpix.com/wp-content/uploads/2016/10/PNGPIX-COM-Pie-Chart-PNG-Image.png";
    private String url = "http://commfiles.com/bayris/sandbox/dashboard/index1.html";
    private String url2 = "http://commfiles.com/bayris/sandbox/dashboard/index2.html";
    private String url3 = "http://commfiles.com/bayris/sandbox/dashboard/index.html";
    private ImageView imageview;
    private WebView webView;
    private Button btn_pie,btn_wide;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.risk_dashboard,container,false);
        MainActivity.iv.setVisibility(View.VISIBLE);
        MainActivity.iv.setImageResource(R.drawable.risk_dashboard);
        MainActivity.logo.setVisibility(View.GONE);
        btn_pie = (Button) view.findViewById(R.id.btn_pie);
        btn_wide = (Button) view.findViewById(R.id.btn_wide);

        btn_pie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.loadUrl(url);
            }
        });

        btn_wide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.loadUrl(url2);
            }
        });
//        imageview = (ImageView) view.findViewById(R.id.img_pie);
//        Glide.with(getActivity()).load(url).into(imageview);

        webView = (WebView) view.findViewById(R.id.webview);
//        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
//        webView.canGoForward();
//        webView.canGoBack();
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new WebViewClient());
//        webView.loadUrl(url3);


        return view;
    }
}
