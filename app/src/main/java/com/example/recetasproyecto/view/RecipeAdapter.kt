import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recetasproyecto.R
import com.example.retrofit_prueba.recetaPrueba.Post

class RecipeAdapter(private val recipes: List<Post>) :
    RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.recipeTitleTextView)
        val ingredientsTextView: TextView = itemView.findViewById(R.id.recipeIngredientsTextView)
        val procedureTextView: TextView = itemView.findViewById(R.id.recipeProcedureTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.titleTextView.text = recipe.nomReceta
        holder.ingredientsTextView.text = recipe.ingredientes
        holder.procedureTextView.text = recipe.procedimiento
    }

    override fun getItemCount() = recipes.size
}
