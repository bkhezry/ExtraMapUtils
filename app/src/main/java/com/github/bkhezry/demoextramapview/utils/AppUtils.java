package com.github.bkhezry.demoextramapview.utils;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.text.Html;
import android.text.Layout;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.github.bkhezry.demoextramapview.R;
import com.github.bkhezry.extramapview.builder.ExtraMarkerBuilder;
import com.github.bkhezry.extramapview.builder.ExtraPolygonBuilder;
import com.github.bkhezry.extramapview.builder.ExtraPolylineBuilder;
import com.github.bkhezry.extramapview.model.ExtraMarker;
import com.github.bkhezry.extramapview.model.ExtraPolygon;
import com.github.bkhezry.extramapview.model.ExtraPolyline;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class AppUtils {
    private static LatLng[] latLngs_1 = {
            new LatLng(35.751221, 51.348371),
            new LatLng(35.749257, 51.371679),
            new LatLng(35.740067, 51.370048),
            new LatLng(35.740812, 51.346795)
    };
    private static LatLng[] latLngs_2 = {
            new LatLng(35.735607, 51.383739),
            new LatLng(35.735842, 51.386496),
            new LatLng(35.723379, 51.388689),
            new LatLng(35.724067, 51.384462)
    };
    private static String[] markerNames = {
            "Marker_1",
            "Marker_2",
            "Marker_3",
            "Marker_4"
    };
    private static int[] icons = {
            R.drawable.ic_person_pin_circle_black_36dp,
            R.drawable.ic_person_pin_circle_black_36dp,
            R.drawable.ic_person_pin_circle_black_36dp,
            R.drawable.ic_person_pin_circle_black_36dp
    };

    public static List<ExtraMarker> getListExtraMarker() {
        List<ExtraMarker> extraMarkers = new ArrayList<>();
        for (int i = 0; i < markerNames.length; i++) {
            extraMarkers.add(
                    new ExtraMarkerBuilder().setName(markerNames[i])
                            .setCenter(latLngs_1[i])
                            .setIcon(icons[i])
                            .build()
            );
        }
        return extraMarkers;
    }

    public static List<ExtraMarker> getListExtraMarker_2() {
        List<ExtraMarker> extraMarkers = new ArrayList<>();
        for (int i = 0; i < markerNames.length; i++) {
            extraMarkers.add(
                    new ExtraMarkerBuilder().setName(markerNames[i])
                            .setCenter(latLngs_2[i])
                            .setIcon(icons[i])
                            .build()
            );
        }
        return extraMarkers;
    }

    public static ExtraPolygon getPolygon_1() {
        return new ExtraPolygonBuilder()
                .setPoints(latLngs_1)
                .setzIndex(0)
                .setStrokeWidth(10)
                .setStrokeColor(Color.argb(100, 255, 255, 255))
                .setFillColor(Color.argb(100, 200, 200, 200))
                .build();
    }

    public static ExtraPolygon getPolygon_2() {
        return new ExtraPolygonBuilder()
                .setPoints(latLngs_2)
                .setzIndex(0)
                .setStrokeWidth(5)
                .setStrokeColor(Color.argb(100, 50, 50, 0))
                .setFillColor(Color.argb(200, 100, 100, 100))
                .build();
    }

    public static ExtraPolyline getPolyline_1() {
        return new ExtraPolylineBuilder()
                .setPoints(latLngs_1)
                .setzIndex(0)
                .setStrokeWidth(5)
                .setStrokeColor(Color.argb(100, 50, 50, 0))
                .build();
    }

    public static ExtraPolyline getPolyline_2() {
        return new ExtraPolylineBuilder()
                .setPoints(latLngs_2)
                .setzIndex(0)
                .setStrokeWidth(10)
                .setStrokeColor(Color.argb(10, 255, 255, 0))
                .build();
    }

    public static void setTextWithLinks(TextView textView, CharSequence html) {
        textView.setText(html);
        textView.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if (action == MotionEvent.ACTION_UP ||
                        action == MotionEvent.ACTION_DOWN) {
                    int x = (int) event.getX();
                    int y = (int) event.getY();

                    TextView widget = (TextView) v;
                    x -= widget.getTotalPaddingLeft();
                    y -= widget.getTotalPaddingTop();

                    x += widget.getScrollX();
                    y += widget.getScrollY();

                    Layout layout = widget.getLayout();
                    int line = layout.getLineForVertical(y);
                    int off = layout.getOffsetForHorizontal(line, x);

                    ClickableSpan[] link = Spannable.Factory.getInstance()
                            .newSpannable(widget.getText())
                            .getSpans(off, off, ClickableSpan.class);

                    if (link.length != 0) {
                        if (action == MotionEvent.ACTION_UP) {
                            link[0].onClick(widget);
                        }
                        return true;
                    }
                }
                return false;
            }
        });
    }

    public static CharSequence fromHtml(String htmlText) {
        return fromHtml(htmlText, false);
    }

    public static CharSequence fromHtml(String htmlText, boolean compact) {
        if (TextUtils.isEmpty(htmlText)) {
            return null;
        }
        CharSequence spanned;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            spanned = Html.fromHtml(htmlText, compact ?
                    Html.FROM_HTML_MODE_COMPACT : Html.FROM_HTML_MODE_LEGACY);
        } else {
            //noinspection deprecation
            spanned = Html.fromHtml(htmlText);
        }
        return trim(spanned);
    }

    private static CharSequence trim(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            return charSequence;
        }
        int end = charSequence.length() - 1;
        while (Character.isWhitespace(charSequence.charAt(end))) {
            end--;
        }
        return charSequence.subSequence(0, end + 1);
    }
}
