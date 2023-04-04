package ph.stacktrek.novare.snakeandladder.medalla.sabellano

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ph.stacktrek.novare.snakeandladder.medalla.sabellano.adapter.WinnersAdapter
import ph.stacktrek.novare.snakeandladder.medalla.sabellano.databinding.FragmentLeaderBoardBinding

class LeaderBoardFragment : Fragment() {
    private var _binding: FragmentLeaderBoardBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLeaderBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val winners = fetchWinners()
        val adapter = WinnersAdapter(winners)
        binding.winnersRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.winnersRecyclerView.adapter = adapter
    }

    private fun fetchWinners(): List<String> {
        val sharedPreferences = requireActivity().getSharedPreferences("winners", Context.MODE_PRIVATE)
        val winnersJson = sharedPreferences.getString("winners", "[]") ?: "[]"
        val winnersList = Gson().fromJson(winnersJson, object : TypeToken<List<String>>() {}.type) as MutableList<String>

        // Return the latest 5 winners
        return if (winnersList.size > 5) {
            winnersList.takeLast(5)
        } else {
            winnersList
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}