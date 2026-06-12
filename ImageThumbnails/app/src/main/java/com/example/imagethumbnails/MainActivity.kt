package com.example.imagethumbnails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import java.util.Locale

// Amber Lawson
// GP Gridview Images
// 06/04/2026
class MainActivity : AppCompatActivity() {
    private lateinit var imgGrid: GridView
    private lateinit var imagesList: List<GridViewModal>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imgGrid = findViewById(R.id.imagesGrid)

        imagesList = buildImagesList()

        val imgAdapter =
            ImageAdapter(this@MainActivity, imagesList)
        imgGrid.adapter = imgAdapter

        imgGrid.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                Toast.makeText(
                    applicationContext,
                    "You clicked ${imagesList[position].imageName}",
                    Toast.LENGTH_LONG).show()
            }
    }

    private fun buildImagesList(): List<GridViewModal> {
        return R.drawable::class.java.fields
            .asSequence()
            .filter { field ->
                val resourceName = field.name
                resourceName.startsWith("img_") ||
                    resourceName.startsWith("snack") ||
                    resourceName.startsWith("wonder")
            }
            .sortedBy { field -> field.name }
            .map { field ->
                val imageId = field.getInt(null)
                GridViewModal(buildDisplayName(field.name), imageId)
            }
            .toList()
    }

    private fun buildDisplayName(resourceName: String): String {
        return when {
            resourceName == "img_eagle" -> getString(R.string.txt_eagle)
            resourceName == "img_elephant" -> getString(R.string.txt_elephant)
            resourceName == "img_gorilla" -> getString(R.string.txt_gorilla)
            resourceName == "img_panda" -> getString(R.string.txt_panda)
            resourceName == "img_panther" -> getString(R.string.txt_panther)
            resourceName == "img_polar" -> getString(R.string.txt_polarbear)
            resourceName.startsWith("img_") -> resourceName
                .removePrefix("img_")
                .replace('_', ' ')
                .titleCaseWords()
            resourceName.startsWith("snack") -> "Snack ${resourceName.removePrefix("snack")}"
            resourceName.startsWith("wonder") -> "Wonder ${resourceName.removePrefix("wonder")}"
            else -> resourceName.replace('_', ' ').titleCaseWords()
        }
    }

    private fun String.titleCaseWords(): String {
        return split(' ').joinToString(" ") { word ->
            word.lowercase(Locale.getDefault()).replaceFirstChar { character ->
                character.titlecase(Locale.getDefault())
            }
        }
    }
}
