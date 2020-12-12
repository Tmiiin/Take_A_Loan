package com.example.takealoan.loanscreen.presentation.adapter

import android.app.PendingIntent.getActivity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.takealoan.R
import com.example.takealoan.loanscreen.data.model.PostLoanModel
import java.text.SimpleDateFormat

class LoanAdapter(
    private var items: List<PostLoanModel>,
    private val onLoanListener: OnLoanListener,
    val context: Context
) :
    RecyclerView.Adapter<LoanAdapter.LoanViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoanViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(
            R.layout.loan_list_element,
            parent,
            false
        ) as View
        return LoanViewHolder(v, onLoanListener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: LoanViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class LoanViewHolder(view: View, private val mOnLoanListener: OnLoanListener) :
        RecyclerView.ViewHolder(view), View.OnClickListener {

        init{
            view.setOnClickListener(this)
        }
        var loanBorrower: TextView = view.findViewById(R.id.loan_borrower)
        var dateOfLoan: TextView = view.findViewById(R.id.date_of_loan)
        val icon: ImageView  = view.findViewById(R.id.icon)
        val amount: TextView = view.findViewById(R.id.amount)

        fun bind(loan: PostLoanModel) {
            val name = loan.firstName + " " + loan.lastName
            if(loan.state == "REGISTERED")
                icon.setImageResource(R.drawable.baseline_schedule_black_18dp)
            else if(loan.state == "APPROVED")
                icon.setImageResource(R.drawable.baseline_check_circle_outline_black_18dp)
            else if(loan.state == "REJECTED")
                icon.setImageResource(R.drawable.baseline_highlight_off_black_18dp)
            loanBorrower.text = name
            val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm")
            val formattedDate: String = formatter.format(parser.parse(loan.date.split(".").first()))
            dateOfLoan.text = formattedDate
            val amounttext =/* getString(R.string.loan_amount) +*/ loan.amount.toString()
            amount.text = amounttext
        }

        override fun onClick(p0: View?) {
            mOnLoanListener.onLoanClick(p0, adapterPosition)
        }

    }
    interface OnLoanListener{
        fun onLoanClick(v: View?, position: Int)
    }
}
