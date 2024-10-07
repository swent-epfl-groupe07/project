package com.github.se.project.model.folder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FolderViewModel(repository: FolderRepository): ViewModel() {
    //TODO
    /*companion object {
        val Factory: ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return FolderViewModel( ... ) as T
                }
            }
    }*/

    val existingFolders: List<Folder> = repository.getFolders()
    var activeFolder: Folder? = null

    fun sortBy(filter: FilterTypes) {
        when (filter) {
            FilterTypes.NAME -> activeFolder?.pdfFiles?.value?.sortBy { it.name }
            FilterTypes.CREATION_UP -> activeFolder?.pdfFiles?.value?.sortBy {
                it.creationTime.timeInMillis
            }
            FilterTypes.CREATION_DOWN -> activeFolder?.pdfFiles?.value?.sortBy {
                -it.creationTime.timeInMillis
            }
            FilterTypes.ACCESS_RECENT -> activeFolder?.pdfFiles?.value?.sortBy {
                it.lastAccess.timeInMillis
            }
            FilterTypes.ACCESS_OLD -> activeFolder?.pdfFiles?.value?.sortBy {
                -it.lastAccess.timeInMillis
            }
            FilterTypes.ACCESS_MOST -> activeFolder?.pdfFiles?.value?.sortBy {
                it.numberAccess
            }
            FilterTypes.ACCESS_LEAST -> activeFolder?.pdfFiles?.value?.sortBy {
                -it.numberAccess
            }
        }
    }
}