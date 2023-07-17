import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class PlaceAdapter(
    context: Context,
    private val placeNames: Array<String>,
    private val placeImages: Array<Int>,
    private val placeDescriptions: Array<String>
) : ArrayAdapter<String>(context, R.layout.list_item_place, placeNames) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView = inflater.inflate(R.layout.list_item_place, parent, false)

        val imageView = rowView.findViewById<ImageView>(R.id.imageView)
        val textViewName = rowView.findViewById<TextView>(R.id.textViewName)
        val textViewDescription = rowView.findViewById<TextView>(R.id.textViewDescription)

        imageView.setImageResource(placeImages[position])
        textViewName.text = placeNames[position]
        textViewDescription.text = placeDescriptions[position]

        return rowView
    }
}
