package tw.org.tseng.vanessaremember;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    String[] aMemo = {   // 預設的備忘內容
            "1. 小乖.按一下可以編輯備忘",
            "2. 長按可以清除備忘" ,
            "3. 生命三要素  :  爸爸,爸爸,爸爸",
            "4. 爸爸電話 0921-887912 ; 媽媽電話 0912-267291",
            "5. 小果子 2239-1341",
            "6. 白雲班 2230-1232 ext 81 or 16"
    };
    ListView lv;  // 顯示備忘錄的 ListView
    ArrayAdapter<String> aa; // ListView 與備忘資料的橋樑

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lv = (ListView) findViewById(R.id.listView);
        aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, aMemo);
        lv.setAdapter(aa);    //設定 listView1 的內容
        //設定 listView1 被按一下的監聽器
        lv.setOnItemClickListener(this);
        //設定 listView1 被長按的監聽器
        lv.setOnItemLongClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View v, int pos, long id) {
        Intent it = new Intent(this, Edit.class);
        it.putExtra("編號",pos+1 );//附加編號
        it.putExtra("nowDate", new Date().toString());
        it.putExtra("備忘", aMemo[pos]); //附加備忘項目的內容
        //startActivity(it);             	 //啟動 Edit 活動
        startActivityForResult(it, pos); //啟動 Edit 並以項目位置為識別碼

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View v, int pos ,long id) {
        aMemo[pos] = (pos+1) + "."; //將內容清除 (只剩編號)
        aa.notifyDataSetChanged();  //通知 Adapter 要更新陣列內容
        return true;     			//傳回 true 表示此事件已處理
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
