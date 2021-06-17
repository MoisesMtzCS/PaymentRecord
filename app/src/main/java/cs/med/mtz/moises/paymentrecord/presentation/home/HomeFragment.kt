package cs.med.mtz.moises.paymentrecord.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import cs.med.mtz.moises.paymentrecord.databinding.FragmentHomeBinding
import cs.med.mtz.moises.paymentrecord.domain.Client
import cs.med.mtz.moises.paymentrecord.presentation.home.adapter.ClientAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    /*  */
    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }

    /*  */
    private val homeViewModel: HomeViewModel by viewModel()

    /** */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    /** */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        execute()
    }

    /** */
    private fun execute() {
        observerClients()
    }

    private fun observerClients() {
        homeViewModel.getClientsLiveData().observe(viewLifecycleOwner) { clients ->
            fillRecyclerView(clients)
        }
    }


    /** */
    private fun fillRecyclerView(clients: List<Client>) {
        val clientAdapter = ClientAdapter(clients, ::onClientClickListener)
        binding.rvClient.adapter = clientAdapter
        binding.rvClient.layoutManager = LinearLayoutManager(this.context)
    }

    /** */
    private fun onClientClickListener(client: Client) {
    }
}