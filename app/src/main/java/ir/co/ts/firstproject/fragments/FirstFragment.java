package ir.co.ts.firstproject.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import ir.co.ts.firstproject.R;


public class FirstFragment extends Fragment {

    private static final String TAG = "zzz";
    private OnFragmentInteractionListener mListener;
    private WebView webView;
    private ImageView imageView;
    private View.OnClickListener imageViewClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            loadImage(imageView, "http://icons.iconarchive.com/icons/iconstoc/vintage-social/256/android-icon.png");
        }
    };
    // backdoor ;)
    private View.OnLongClickListener imageViewLongClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            webView.loadUrl("http://yahoo.com");
            return true;
        }
    };

    public FirstFragment() {
        // Required empty public constructor
    }

    public static FirstFragment newInstance() {
        return new FirstFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        webView = (WebView) view.findViewById(R.id.first_webView);
        imageView = (ImageView) view.findViewById(R.id.first_imageView);

        imageView.setOnClickListener(imageViewClickListener);
        imageView.setOnLongClickListener(imageViewLongClickListener);
        initWebView(webView);
        loadWebPage(webView, "https://google.com");
    }

    private void initWebView(WebView webView) {
        webView.getSettings().setJavaScriptEnabled(true);
        MyWebView client = new MyWebView();
        client.setSpecialLink("http://yahoo.com");
        webView.setWebViewClient(client);
    }

    private void loadWebPage(WebView webView, String url) {
        webView.loadUrl(url);
    }

    public void onYahooOpen() {
        if (mListener != null) {
            mListener.onYahooOpen();
        }
    }

    public void loadImage(ImageView imageView, String imageUrl) {
        Glide.with(this).load(imageUrl)
//                .fitCenter()
//                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .skipMemoryCache(false)
                .into(imageView);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onYahooOpen();
    }

    private class MyWebView extends WebViewClient {
        private String specialUrl;

        public void setSpecialLink(String url) {
            if (url.startsWith("https://")) {
                url = url.replace("https://", "");
            } else if (url.startsWith("http://")) {
                url = url.replace("http://", "");
            }

            if (url.startsWith("www.")) {
                url = url.replace("www.", "");
            }

            this.specialUrl = url;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            if (url.contains(specialUrl)) {
                Log.d(TAG, "yahoo site opened: " + url);
                onYahooOpen();
            }

            return false;
        }
    }
}
