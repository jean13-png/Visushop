package com.artcraft.photocraft;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

/** Launch-time invitation. Joining a community is always optional. */
final class VisushopWelcome {
    static final String WHATSAPP = "https://whatsapp.com/channel/0029Vb9ngS3KAwEqQzI5v50u";
    static final String FACEBOOK = "https://www.facebook.com/profile.php?id=61587574838467";
    private static AlertDialog dialog;

    static void show(Activity activity, Runnable onContinue) {
        if (activity.isFinishing() || (dialog != null && dialog.isShowing())) return;
        int padding = dp(activity, 22);
        LinearLayout column = new LinearLayout(activity);
        column.setOrientation(LinearLayout.VERTICAL);
        column.setPadding(padding, padding, padding, padding);
        column.setBackgroundColor(Color.rgb(23, 25, 32));

        TextView title = new TextView(activity);
        title.setText("VISUSHOP");
        title.setTextSize(28);
        title.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        title.setTextColor(Color.WHITE);
        title.setGravity(Gravity.CENTER);
        column.addView(title);

        TextView subtitle = new TextView(activity);
        subtitle.setText("Rejoignez notre communauté pour découvrir les nouveautés et échanger avec d’autres créateurs.");
        subtitle.setTextColor(Color.rgb(210, 213, 224));
        subtitle.setTextSize(15);
        subtitle.setGravity(Gravity.CENTER);
        subtitle.setPadding(0, dp(activity, 12), 0, dp(activity, 16));
        column.addView(subtitle);

        addButton(activity, column, "Rejoindre sur WhatsApp", true, () -> open(activity, WHATSAPP));
        addButton(activity, column, "Rejoindre sur Facebook", true, () -> open(activity, FACEBOOK));
        addButton(activity, column, "X — bientôt disponible", false, null);
        addButton(activity, column, "GitHub — bientôt disponible", false, null);
        addButton(activity, column, "Continuer vers l’éditeur", true, () -> {
            AlertDialog current = dialog;
            if (current != null) current.dismiss();
            onContinue.run();
        });

        ScrollView scroll = new ScrollView(activity);
        scroll.addView(column);
        AlertDialog instance = new AlertDialog.Builder(activity).setView(scroll).create();
        instance.setCanceledOnTouchOutside(false);
        instance.setOnCancelListener(ignored -> onContinue.run());
        instance.setOnDismissListener(ignored -> { if (dialog == instance) dialog = null; });
        dialog = instance;
        instance.show();
    }

    private static void addButton(Activity activity, LinearLayout column, String label, boolean enabled, Runnable action) {
        Button button = new Button(activity);
        button.setText(label);
        button.setAllCaps(false);
        button.setTextColor(enabled ? Color.WHITE : Color.LTGRAY);
        button.setEnabled(enabled);
        GradientDrawable background = new GradientDrawable();
        background.setColor(enabled ? Color.rgb(53, 79, 155) : Color.rgb(55, 57, 65));
        background.setCornerRadius(dp(activity, 9));
        button.setBackground(background);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, dp(activity, 50));
        params.setMargins(0, 0, 0, dp(activity, 8));
        column.addView(button, params);
        if (action != null) button.setOnClickListener(view -> action.run());
    }

    private static void open(Activity activity, String url) {
        try {
            activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        } catch (ActivityNotFoundException error) {
            Toast.makeText(activity, "Aucune application disponible pour ouvrir ce lien.", Toast.LENGTH_LONG).show();
        }
    }

    private static int dp(Activity activity, int value) {
        return Math.round(value * activity.getResources().getDisplayMetrics().density);
    }

    private VisushopWelcome() {}
}
