package tw.org.tseng.vanessaremember;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Edit extends AppCompatActivity {
    TextView txv;
    EditText edt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_edit);


        Intent it = getIntent(); //取得傳入的 Intent 物件
        int no = it.getIntExtra("編號", 0); //讀出名為 "編號" 的 Int 資料, 若沒有則傳回 0
        String s = it.getStringExtra("備忘");  //讀出名為 "備忘" 的 String 資料
        String ndt = it.getStringExtra("nowDate");

        txv = (TextView) findViewById(R.id.textView);
        edt = (EditText) findViewById(R.id.editText);


        txv.setText(no + ".");//在畫面左上角顯示編號
        if (s.length() > 3) {
            edt.setText(s.substring(3)+"\n" +ndt);
        }//將傳來的備忘資料去除前3個字, 然後填入編輯元件中

    }
    public void onCancel(View v) {  //按取消鈕時
        setResult(RESULT_CANCELED); // 傳回代表取消的結果碼
        finish();    //結束活動
    }

    public void onSave(View v) {    //按儲存鈕時
        Intent it2 = new Intent();
        it2.putExtra("備忘", txv.getText() + " " + edt.getText()); // 附加項目編號與修改後的內容
        setResult(RESULT_OK, it2);                                 // 傳回代表成功的結果碼, 以及修改的資料
        finish();    //結束活動
    }

}
