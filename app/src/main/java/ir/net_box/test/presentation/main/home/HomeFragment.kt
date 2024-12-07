package ir.net_box.test.presentation.main.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.map
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import kotlin.jvm.java
import ir.net_box.test.R
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.getValue

@AndroidEntryPoint
class HomeFragment : Fragment() {

    val viewModel: PlayListViewmodel by viewModels()

    lateinit var txtTitle: TextView
    lateinit var txtSubTitle: TextView
    lateinit var txtDescription: TextView

    lateinit var imgBanner: ImageView
    lateinit var listFragment: ListFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate( R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)
    }

     fun init(view: View) {

        imgBanner = view.findViewById( R.id.img_banner)
        txtTitle = view.findViewById( R.id.title)
        txtSubTitle = view.findViewById( R.id.subtitle)
        txtDescription = view.findViewById( R.id.description)


        listFragment =  ListFragment()
        val transaction = childFragmentManager.beginTransaction()
        transaction.add( R.id.list_fragment, listFragment)
        transaction.commit()


         lifecycleScope.launch {
             viewModel.playlists.collectLatest { pagingData ->
                 // Collect PagingData and submit it to adapter
                 Log.d("949494", "init: $pagingData")
//                 listFragment.bindData(playList)
             }
         }


         listFragment.setOnContentSelectedListener {
         }

         listFragment.setOnItemClickListener {

         }
    }

}