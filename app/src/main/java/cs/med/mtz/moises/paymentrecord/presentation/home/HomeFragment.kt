package cs.med.mtz.moises.paymentrecord.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import cs.med.mtz.moises.paymentrecord.R
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
    override fun onStart() {
        super.onStart()
        execute()
    }

    /** */
    private fun execute() {
        observerClients()
        toRegisterClientClickListenet()
    }

    /** */
    private fun observerClients() {
        homeViewModel.getClientsLiveData().observe(viewLifecycleOwner) { clients ->
            fillRecyclerView(clients)
        }
    }


    /** */
    private fun fillRecyclerView(clients: List<Client>) {
        if (clients.isEmpty()) {
            binding.messageNoGoals.visibility = View.VISIBLE
            binding.ivSad.visibility = View.VISIBLE
        }
        val clientAdapter = ClientAdapter(clients, ::onClientClickListener)
        binding.rvClient.adapter = clientAdapter
        binding.rvClient.layoutManager = LinearLayoutManager(this.context)
    }

    /** */
    private fun onClientClickListener(client: Client) {
        val direction = HomeFragmentDirections.actionHomeFragmentToProfileActivity(client)
        findNavController().navigate(direction)
    }

    private fun toRegisterClientClickListenet(){
        binding.createClientButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_registrationFragment)
        }
    }
}