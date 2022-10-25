package cc.thornbird.s3upload

import android.os.Bundle
import android.os.Build
import android.os.StrictMode
import android.os.Parcelable

import android.app.Activity
import android.content.Intent
import android.util.Log

import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore

class S3Upload : Activity() {
	    val TAG = "S3Upload"
	    val s3 = S3Client()

	    override fun onCreate(savedInstanceState: Bundle?) {
	        super.onCreate(savedInstanceState)
	        
		if (android.os.Build.VERSION.SDK_INT >9){
			val policy =StrictMode.ThreadPolicy.Builder.permitAll().build()
			StrictMode.setThreadPolicy(policy)
		}

//		setContentView(R.layout.s3upload_activity)
	    }

	    override fun onResume() {
		super.onResume()
		
		val intent = getIntent()
		if (intent.type?.startsWith("image/") == true)
			handleSendImage(intent)		

	    }

	    private fun getRealPathFromUri(contentUri: Uri): String
	    {
		var cursor: Cursor? = null
		try
		{
			val proj = arrayOf(MediaStore.Images.Media.DATA)
			cursor = getContentResolver.query(contentUri,proj,null,null,null)
			val column_index = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
			cursor!!.moveToFirst()

			return cursor!!.getString(column_index)
		}
		finally
		{
			if(cursor != null)
				cursor!!.close()
		}
	    }
	    private fun handleSendImage(intent: Intent){
		Log.i(TAG,"send image.")
		(intent.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM) as? Uri)?.let{
			s3.Upload(getRealPathFromUri(it))
		}
	    }
}


