package ru.antdroid

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.antdroid.data.DataCurrency
import ru.antdroid.data.Stock

class MainActivity : AppCompatActivity() {

    private val mDelayHandler: Handler = Handler()
    private lateinit var rAdapter: CurrencyRecyclerAdapter
    private val currencyList = ArrayList<Stock>()
    private val refreshDelay: Long = 15000
    private val mRunnable: Runnable = Runnable {
        if (!isFinishing) {
            loadCurrency()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.layoutManager = LinearLayoutManager(recycler_view.context)
        rAdapter = CurrencyRecyclerAdapter(currencyList)
        recycler_view.adapter = rAdapter

        mDelayHandler.postDelayed(mRunnable, refreshDelay)

        loadCurrency()
    }

    override fun onDestroy() {
        mDelayHandler.removeCallbacks(mRunnable)
        super.onDestroy()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_refresh -> {
                loadCurrency()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun loadCurrency() {
        val groupsFromApi = ApiRequest.getRequestApi().getCurrency()
        groupsFromApi.enqueue(object : Callback<DataCurrency> {
            override fun onResponse(call: Call<DataCurrency>, response: Response<DataCurrency>) {
                if (response.isSuccessful) {
                    refreshData(response.body())
                    showMessage("all data is load")
                } else {
                    showMessage("response code ${response.code()}")
                }
            }

            override fun onFailure(call: Call<DataCurrency>, t: Throwable) {
                showMessage("Trouble: ${t.message}")
            }
        })
    }

    private fun showMessage(message: String) {
        Log.d("ERROR_LOG", message)
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun refreshData(data: DataCurrency?) {
        currencyList.clear()
        if (data != null) {
            currencyList.addAll(data.stock)
            rAdapter.notifyDataSetChanged()
        }
    }


}
