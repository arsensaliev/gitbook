package arsensaliev.io.gitbook.ui.converter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import arsensaliev.io.gitbook.mvp.model.converter.IConverter
import arsensaliev.io.gitbook.mvp.model.converter.Image
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.util.*

class AndroidConverter(val context: Context?) : IConverter {
    override fun convert(image: Image): Completable = Completable.fromAction {
        context?.let { context ->
            try {
                Thread.sleep(5000)
            } catch (e: InterruptedException) {
                return@let
            }

            val bitmap = BitmapFactory.decodeByteArray(image.data, 0, image.data.size)
            var file = context.getDir(Environment.DIRECTORY_DOWNLOADS, Context.MODE_PRIVATE)
            file = File(file, "${UUID.randomUUID()}.jpg")

            try {
                val stream: OutputStream = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
                stream.flush()
                stream.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }.subscribeOn(Schedulers.computation())
}