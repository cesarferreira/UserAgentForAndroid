package cesarferreira.useragentforandroid

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import cesarferreira.useragentforandroid.library.DeviceInfo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var disposable: Disposable? = null

    private val wikiApiServe by lazy { WikiApiService.create(applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_search.setOnClickListener {
            if (edit_search.text.toString().isNotEmpty()) {
                beginSearch(edit_search.text.toString())
            }
        }

        val deviceInfo = DeviceInfo()
        // D/OkHttp: User-Agent: DeliveryApp/1.0.0 (Android Pie 9.0; ONEPLUS A6000)

        val deviceName = deviceInfo.getDeviceName()
        val androidName = deviceInfo.currentAndroidName()
        val androidRelease = deviceInfo.androidReleaseNumber()

        Log.i("info", "deviceName: $deviceName")
        Log.i("info", "androidName: $androidName")
        Log.i("info", "androidRelease: $androidRelease")
    }

    @SuppressLint("SetTextI18n")
    private fun beginSearch(searchString: String) {
        disposable = wikiApiServe.hitCountCheck("query", "json", "search", searchString)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> txt_search_result.text = "${result.query.searchinfo.totalhits} result found" },
                { error -> Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show() }
            )
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }
}
