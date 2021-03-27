package arsensaliev.io.gitbook.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubRepository(
    @Expose val id: String? = null,
    @Expose val name: String? = null,
    @Expose val forksCount: String? = null
) : Parcelable