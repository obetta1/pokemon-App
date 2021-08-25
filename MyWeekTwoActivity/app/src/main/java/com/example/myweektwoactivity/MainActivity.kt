	    package com.example.myweektwoactivity
		import android.content.res.Configuration
        import android.os.*
        import android.os.Handler
        import android.os.Looper
        import androidx.appcompat.app.AppCompatActivity
        import kotlinx.android.synthetic.main.activity_main.*

        class MainActivity : AppCompatActivity() {

		var portraitCount = 0
		var landscapeCount = 0

			var handler = Handler(Looper.getMainLooper())
		override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
			setContentView(R.layout.activity_main)

			if(savedInstanceState != null){
				landscapeCount = savedInstanceState.getInt("landscapeCount")
				portraitCount = savedInstanceState.getInt("portraitCount")
			}
		}
			// this code saves the current value of the count of the different configuration
			// by overriding the onSaveInstanceState method
		override fun onSaveInstanceState(outState: Bundle) {
			super.onSaveInstanceState(outState)
			outState.putInt("landscapeCount", landscapeCount)
			outState.putInt("portraitCount", portraitCount)
		}
		override fun onStart() {
			super.onStart()
			firstTextView.text = "  onStart Mode"
			}
            override fun onResume() {
			super.onResume()
				
            //this set of code  checks the configuration status of the activity
			if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
			    landscapeCount += 1
                secondTextView.text = " Landscape View  $landscapeCount"
			} else if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
				portraitCount ++
				secondTextView.text = " portrait View $portraitCount"
			}
			firstTextView.text = "  onResume"

            }
		// i called a delay function so that the onPause state can visible for a while
		override fun onPause() {
			super.onPause()
			handler.postDelayed({ firstTextView.text = " onRestart Mode"},  1500)
		}
            // this restarts the activity
			// i called a delay function so that the onRestart state can visible for a while
		override fun onRestart() {
			super.onRestart()
			if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
				landscapeCount --
			}
            else if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
				portraitCount --
			}
				handler.postDelayed({ firstTextView.text = " onRestart Mode"},  500)
		}
		override fun onStop() {
			super.onStop()
			firstTextView.text = " onStop Mode"
		}
	}