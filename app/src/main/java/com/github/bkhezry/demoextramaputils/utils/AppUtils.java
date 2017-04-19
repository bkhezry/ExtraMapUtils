package com.github.bkhezry.demoextramaputils.utils;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.IdRes;
import android.text.Html;
import android.text.Layout;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.github.bkhezry.demoextramaputils.R;
import com.github.bkhezry.extramaputils.builder.ExtraMarkerBuilder;
import com.github.bkhezry.extramaputils.builder.ExtraPolygonBuilder;
import com.github.bkhezry.extramaputils.builder.ExtraPolylineBuilder;
import com.github.bkhezry.extramaputils.model.ExtraMarker;
import com.github.bkhezry.extramaputils.model.ExtraPolygon;
import com.github.bkhezry.extramaputils.model.ExtraPolyline;
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
    private static LatLng[] latLngs_3 = {
            new LatLng(35.70059, 51.37799),
            new LatLng(35.70139, 51.4052),
            new LatLng(35.69568, 51.40417),
            new LatLng(35.6962, 51.39171),
            new LatLng(35.68874, 51.39297),
            new LatLng(35.6881, 51.39343),
            new LatLng(35.6871, 51.40271),
            new LatLng(35.67984, 51.40129)
    };
    private static String[] markerNames = {
            "Marker_1",
            "Marker_2",
            "Marker_3",
            "Marker_4"
    };
    private static
    @IdRes
    int[] icons_1 = {
            R.drawable.ic_person_pin_circle_black_36dp,
            R.drawable.ic_person_pin_circle_black_36dp,
            R.drawable.ic_person_pin_circle_black_36dp,
            R.drawable.ic_person_pin_circle_black_36dp
    };
    private static
    @IdRes
    int[] icons_2 = {
            R.drawable.ic_directions_bike_blue_grey_900_36dp,
            R.drawable.ic_directions_bike_blue_grey_900_36dp
    };

    public static List<ExtraMarker> getListExtraMarker() {
        List<ExtraMarker> extraMarkers = new ArrayList<>();
        for (int i = 0; i < markerNames.length; i++) {
            extraMarkers.add(
                    new ExtraMarkerBuilder()
                            .setName(markerNames[i])
                            .setCenter(latLngs_1[i])
                            .setIcon(icons_1[i])
                            .setIconColor(Color.WHITE)
                            .build()
            );
        }
        return extraMarkers;
    }

    public static List<ExtraMarker> getListMarker() {
        List<ExtraMarker> extraMarkers = new ArrayList<>();
        extraMarkers.add(new ExtraMarkerBuilder()
                .setName("Start")
                .setCenter(latLngs_3[0])
                .setIcon(icons_2[0])
                .setIconColor(Color.YELLOW)
                .build()
        );
        extraMarkers.add(new ExtraMarkerBuilder()
                .setName("End")
                .setCenter(latLngs_3[latLngs_3.length - 1])
                .setIcon(icons_2[1])
                .setIconColor(Color.YELLOW)
                .build()
        );
        return extraMarkers;
    }

    public static List<ExtraMarker> getListExtraMarker_2() {
        List<ExtraMarker> extraMarkers = new ArrayList<>();
        for (int i = 0; i < markerNames.length; i++) {
            extraMarkers.add(
                    new ExtraMarkerBuilder().setName(markerNames[i])
                            .setCenter(latLngs_2[i])
                            .setIcon(icons_1[i])
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
                .setStrokeColor(Color.BLACK)
                .setFillColor(Color.argb(100, 200, 200, 200))
                .build();
    }

    public static ExtraPolygon getPolygon_2() {
        return new ExtraPolygonBuilder()
                .setPoints(latLngs_2)
                .setzIndex(0)
                .setStrokeWidth(5)
                .setStrokeColor(Color.BLACK)
                .setFillColor(Color.argb(200, 100, 100, 100))
                .build();
    }

    public static ExtraPolyline getPolyline_1() {
        return new ExtraPolylineBuilder()
                .setPoints(latLngs_1)
                .setzIndex(0)
                .setStrokeWidth(5)
                .setStrokeColor(Color.BLACK)
                .build();
    }

    public static ExtraPolyline getPolyline_2() {
        return new ExtraPolylineBuilder()
                .setPoints(latLngs_2)
                .setzIndex(0)
                .setStrokeWidth(10)
                .setStrokeColor(Color.BLACK)
                .build();
    }

    public static ExtraPolyline getPolyline_3() {
        return new ExtraPolylineBuilder()
                .setPoints(latLngs_3)
                .setzIndex(0)
                .setStrokeWidth(10)
                .setStrokeColor(Color.BLACK)
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
