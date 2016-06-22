package com.example.fileio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button bt1, bt2;
	private EditText et1, et2;
	
	private static final String FILENAME = "temp_file.txt";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bt1 = (Button) this.findViewById(R.id.bt1);
		bt2 = (Button) this.findViewById(R.id.bt2);
		et1 = (EditText) this.findViewById(R.id.et1);
		et2 = (EditText) this.findViewById(R.id.et2);
		bt1.setOnClickListener(new MySetOnClickListener());
		bt2.setOnClickListener(new MySetOnClickListener());
	}

	private class MySetOnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			File file = new File(Environment.getExternalStorageDirectory(),FILENAME);
			switch(v.getId()){
			case R.id.bt1:
				if (Environment.getExternalStorageState().equals(
						Environment.MEDIA_MOUNTED)) {

					try {
						FileOutputStream fos = new FileOutputStream(file, true);
						fos.write(et1.getText().toString().getBytes());
						fos.close();
						Toast.makeText(MainActivity.this, "写入文件成功",
								Toast.LENGTH_LONG).show();
					} catch (Exception e) {
						Toast.makeText(MainActivity.this, "写入文件失败",
								Toast.LENGTH_SHORT).show();
					}
				} else {
					// 此时SDcard不存在或者不能进行读写操作的
					Toast.makeText(MainActivity.this,
							"此时SDcard不存在或者不能进行读写操作", Toast.LENGTH_SHORT).show();
				}
				break;
			case R.id.bt2:
				if (Environment.getExternalStorageState().equals(
						Environment.MEDIA_MOUNTED)) {
					try {
						FileInputStream inputStream = new FileInputStream(file);
						byte[] b = new byte[inputStream.available()];
						inputStream.read(b);
						et2.setText(new String(b));
						Toast.makeText(MainActivity.this, "读取文件成功",
								Toast.LENGTH_LONG).show();
					} catch (Exception e) {
						Toast.makeText(MainActivity.this, "读取失败",
								Toast.LENGTH_SHORT).show();
					}
				} else {
					// 此时SDcard不存在或者不能进行读写操作的
					Toast.makeText(MainActivity.this,
							"此时SDcard不存在或者不能进行读写操作", Toast.LENGTH_SHORT).show();
				}
				break;
			}
		}
	}
}
