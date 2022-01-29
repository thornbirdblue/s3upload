package cc.thornbird.s3upload

import android.os.Bundle
import android.app.Activity
import android.content.Intent
import android.util.Log

class S3Upload : Activity() {
	    val TAG = "S3Upload"

	    override fun onCreate(savedInstanceState: Bundle?) {
		val intent = getIntent()
	        super.onCreate(savedInstanceState)
	        
		if (intent.type?.startsWith("image/") == true)
			handleSendImage(intent)		

//		setContentView(R.layout.s3upload_activity)
	    }

	    private fun handleSendImage(intent: Intent){
		Log.i(TAG,"send image.")
			
	    }
}


