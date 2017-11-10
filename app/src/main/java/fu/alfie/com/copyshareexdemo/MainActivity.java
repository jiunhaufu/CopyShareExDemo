package fu.alfie.com.copyshareexdemo;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = (TextView)findViewById(R.id.textView);
        textView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("Label", textView.getText());
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(MainActivity.this, "複製內文", Toast.LENGTH_LONG).show();
                return true;
            }
        });

        final TextView textView1 = (TextView)findViewById(R.id.textView3);
        textView1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain"); //mimeType
                share.putExtra(Intent.EXTRA_TEXT, textView1.getText());
                startActivity(Intent.createChooser(share, "將訊息分享至"));
                return true;
            }
        });

        final TextView textView2 = (TextView)findViewById(R.id.textView4);
        textView2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                ShareCompat.IntentBuilder.from(MainActivity.this)
                        .setChooserTitle("將訊息分享至")
                        .setType("text/plain") //mimeType
                        .setText(textView2.getText())
                        .startChooser();
                return true;
            }
        });
    }
}
