package com.example.takealoan.loanscreen.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.takealoan.R
import com.example.takealoan.loanscreen.data.model.PostLoanModel
import com.example.takealoan.loanscreen.di.LoanListPresenterFactory
import com.example.takealoan.loanscreen.presentation.adapter.LoanAdapter
import com.example.takealoan.loanscreen.presentation.interfaces.LoanListPresenter
import com.example.takealoan.loanscreen.presentation.interfaces.LoanListView
import kotlinx.android.synthetic.main.create_loan.*
import kotlinx.android.synthetic.main.loan_list_layout.*


class LoanListFragment() : Fragment(), LoanListView, LoanAdapter.OnLoanListener {

    private var listOfDetails: MutableList<PostLoanModel> =
        arrayListOf() //list of item list views
    lateinit var detailsAdapter: LoanAdapter

    //переменная для отслеживания времени (выход с приложения по двойному нажатию "назад")
    private var back_pressed: Long = 0
    private var presenter: LoanListPresenter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initPresenter()
        return inflater.inflate(R.layout.loan_list_layout, null)
    }

    override fun setListOfDetails(list: List<PostLoanModel>) {
        this.listOfDetails.clear()
        this.listOfDetails.addAll(list)
        detailsAdapter.notifyDataSetChanged()
    }

    fun initPresenter() {
        presenter = context?.let { LoanListPresenterFactory.create(it) }
        presenter?.attachView(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        detailsAdapter = context?.let { LoanAdapter(listOfDetails, this, it) }!!
        loan_list_rv.adapter = detailsAdapter
        loan_list_rv.layoutManager = LinearLayoutManager(context)
        presenter?.getLoanList()
        fab.setOnClickListener { view ->
            onFabClick(view)
        }
        // This callback will only be called when MyFragment is at least Started.
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (back_pressed + 2000 > System.currentTimeMillis())
                        activity?.finish()
                    else Toast.makeText(
                        context, "Press once again to exit!",
                        Toast.LENGTH_SHORT
                    ).show()
                    back_pressed = System.currentTimeMillis()
                }
            }
        // The callback can be enabled or disabled here or in handleOnBackPressed()
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onLoanClick(v: View?, position: Int) {
        val bundle = Bundle()
        val id = listOfDetails[position].id
        bundle.putInt("id", id)
        val action = R.id.action_loanListFragment_to_showLoanDetailsFragment
        Navigation.findNavController(v!!).navigate(action, bundle)
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        super.onDestroy()
    }

    private fun onFabClick(it: View) {
        fab.visibility = View.GONE
        val action = R.id.action_loanListFragment_to_createLoanFragment
        Navigation.findNavController(it).navigate(action)
    }

}
