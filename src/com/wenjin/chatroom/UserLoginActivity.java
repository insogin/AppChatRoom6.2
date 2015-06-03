package com.wenjin.chatroom;

import java.util.List;

import com.wenjin.bean.User;
import com.wenjin.chatroom.R;
import com.wenjin.service.UserService;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class UserLoginActivity extends Activity {
	private Spinner spin_name; //向下滚动菜单
	private UserService service;
	private ImageView view_image;
	private User curUser;	//保存当前选择的用户
	private Intent intentRegister;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {// Bundle class:A mapping from String values to various Parcelable types.
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.welcome); //新的 welcome 界面
        service = new UserService(this);
        spin_name = (Spinner) findViewById(R.id.name); //name
        view_image = (ImageView) findViewById(R.id.avatar); //picture
        spin_name.setOnItemSelectedListener(new SpinnerItemClickListner());
        
		intentRegister = new Intent(UserLoginActivity.this, UserRegisActivity.class);
		
        initDatas();
	}
	
	private final class SpinnerItemClickListner implements OnItemSelectedListener{
		public void onItemSelected(AdapterView<?> parent, View view, int position,
				long id) {
			Spinner spinner = (Spinner) parent; //Spinner: subclass of AdapterView
			curUser = (User) spinner.getItemAtPosition(position);
			Bitmap bitmap = BitmapFactory.decodeFile(curUser.getImg());
			view_image.setImageBitmap(bitmap); //用户头像
		}
		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			
		}
	}
	
	/**
	 * 初始化已注册的用户
	 */
	private void initDatas() {
		final List<User> userList = service.queryResigterUser();
		// 存在已注册的用户，否则跳转到注册页面
		if(userList.size() > 0){
			spin_name.setAdapter(new SpinnerNameAdapter(this, userList,
					R.layout.spinner));
			curUser = userList.get(0); // 0 is the location
			Bitmap bitmap = BitmapFactory.decodeFile(curUser.getImg());
			view_image.setImageBitmap(bitmap);
		}
		else
		{
			startActivity(intentRegister);
		}
	}

	/**
	 * 处理取消按钮事件
	 * @author Administrator
	 */
	public void endLogin(View view){
		this.finish();
	}
	
	public void chat_back(View view){
		this.finish();
	}
	private Handler handle = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == 1) {
				Intent intent = new Intent(UserLoginActivity.this,
						ChatActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);         //
				startActivity(intent);
			}
		}
	};
	
	/**
	 * 处理登录按钮事件
	 * @author Administrator
	 */
	public void startLogin(View view){
		final String ip = getResources().getString(R.string.ip);
		final String port = getResources().getString(R.string.port);
		
		curUser.setIp(ip);
		curUser.setPort(port);
		curUser.setFlag(1);                                      //
		new Thread(new Runnable() {
			@Override
			public void run() {
				service.convertUser(curUser);
				handle.sendEmptyMessage(1);//
			}
		}).start();
	}
	
	private final class SpinnerNameAdapter extends BaseAdapter{
		private List<User> list;
		private int resource;
		private LayoutInflater inflater;
		public SpinnerNameAdapter(Context context,
				List<User> userList, int spinnerItem) {
			this.list = userList;
			this.resource = spinnerItem;
			this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView textView;
			if(convertView == null){
				convertView = inflater.inflate(resource, null);
				textView = (TextView) convertView.findViewById(R.id.name);
				ViewHolder holder = new ViewHolder();
				holder.textView = textView;
				convertView.setTag(holder);
			}else{
				ViewHolder holder = (ViewHolder) convertView.getTag();
				textView = holder.textView;
			}
			User user = list.get(position);
			textView.setText(user.getName());
			return convertView;
		}

		@Override
		public View getDropDownView(int position, View convertView,
				ViewGroup parent) {
			TextView textView;
			ImageView imageView;
			if(convertView == null){
				convertView = inflater.inflate(R.layout.spinner_item, null);
				textView = (TextView) convertView.findViewById(R.id.name);
				imageView = (ImageView) convertView.findViewById(R.id.image);
				ViewHolder holder = new ViewHolder();
				holder.textView = textView;
				holder.imageView = imageView;
				convertView.setTag(holder);
			}else{
				ViewHolder holder = (ViewHolder) convertView.getTag();
				textView = holder.textView;
				imageView = holder.imageView;
			}
			User user = list.get(position);
			Bitmap bitmap = BitmapFactory.decodeFile(user.getImg());
			textView.setText(user.getName());
			imageView.setImageBitmap(bitmap);
			return convertView;
		}
		
	}
	
	private class ViewHolder{
		TextView textView;
		ImageView imageView;
	}
	
	public void registerBtn(View view){
		startActivity(intentRegister);
	}

/*	public void circleAvatar() {
		ImageView image;
		image = (ImageView)findViewById(R.id.avatar);

		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.copyright);
		Bitmap output = toRoundCorner(bitmap, 15.0f);
		image.setImageBitmap(output);
	}

	public static Bitmap toRoundCorner(Bitmap bitmap, float pixels) {  
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);  
		Canvas canvas = new Canvas(output);  

		final int color = 0xff424242;  
		final Paint paint = new Paint();  
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());  
		final RectF rectF = new RectF(rect);  
		final float roundPx = pixels;  

		paint.setAntiAlias(true);  
		canvas.drawARGB(0, 0, 0, 0);  

		paint.setColor(color);  
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);  
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));  
		canvas.drawBitmap(bitmap, rect, rect, paint);  

		return output;  
	}*/
}
