package cs.med.mtz.moises.paymentrecord.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cs.med.mtz.moises.paymentrecord.databinding.ViewHolderClientBinding
import cs.med.mtz.moises.paymentrecord.domain.Client

class ClientAdapter(
    private val clients: List<Client>,
    private val onClientActionClick: (Client) -> Unit,

    ) : RecyclerView.Adapter<ClientAdapter.ClientViewHolder>() {

    class ClientViewHolder(var binding: ViewHolderClientBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        val binding =
            ViewHolderClientBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ClientViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        val client: Client = clients[position]
        holder.binding.tvName.text = client.name
    }

    override fun getItemCount(): Int {
        return clients.size
    }

}