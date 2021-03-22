package arsensaliev.io.gitbook.ui.fragment

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import arsensaliev.io.gitbook.databinding.FragmentConverterBinding
import arsensaliev.io.gitbook.mvp.model.converter.Image
import arsensaliev.io.gitbook.mvp.presenter.ConverterPresenter
import arsensaliev.io.gitbook.mvp.view.ConverterView
import arsensaliev.io.gitbook.ui.App
import arsensaliev.io.gitbook.ui.BackButtonListener
import arsensaliev.io.gitbook.ui.converter.AndroidConverter
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class ConverterFragment : MvpAppCompatFragment(), ConverterView, BackButtonListener {
    companion object {
        fun newInstance() = ConverterFragment()
        private const val PICK_IMAGE_REQUEST_ID = 201
    }

    private var ui: FragmentConverterBinding? = null

    private val presenter by moxyPresenter {
        ConverterPresenter(App.instance.router, Schedulers.computation(), AndroidConverter(context))
    }

    override fun backPressed(): Boolean = presenter.backPressed()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentConverterBinding.inflate(inflater, container, false).also { ui = it }.root

    override fun init() {
        ui?.btnConvert?.setOnClickListener {
            presenter.convertClick()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        ui = null
    }

    override fun pickImage() {
        val intent = Intent().apply {
            type = "image/*"
            action = Intent.ACTION_GET_CONTENT
        }

        startActivityForResult(
            Intent.createChooser(
                intent,
                "Select Picture"
            ),
            PICK_IMAGE_REQUEST_ID
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PICK_IMAGE_REQUEST_ID) {
            if (resultCode == Activity.RESULT_OK) {
                data?.data?.let { uri ->
                    val bytes = context?.contentResolver
                        ?.openInputStream(uri)?.buffered()
                        ?.use { it.readBytes() }

                    bytes?.let {
                        presenter.imageSelected(Image(bytes))
                    }
                }
            }
        }
    }

    var convertInProgressDialog: Dialog? = null


    override fun showConvertInProgress() {
        context?.let {
            convertInProgressDialog = AlertDialog.Builder(it)
                .setMessage("Идет конвертация...")
                .setNegativeButton("Отмена") { _, _ -> presenter.convertCancel() }
                .create()

            convertInProgressDialog?.show()
        }
    }

    override fun hideConvertInProgress() {
        convertInProgressDialog?.dismiss()
    }

    override fun showConvertSuccess() {
        Toast.makeText(context, "Конвертация завершена", Toast.LENGTH_SHORT).show()
    }

    override fun showConvertCancel() {
        Toast.makeText(context, "Конвертация отменена", Toast.LENGTH_SHORT).show()
    }

    override fun showConvertError() {
        Toast.makeText(context, "Ошибка при конвертации", Toast.LENGTH_SHORT).show()
    }
}