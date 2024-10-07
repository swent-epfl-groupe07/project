package com.github.se.project.model.folder

import com.ibm.icu.util.Calendar
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertSame



class FolderViewModelTest {
    private lateinit var folderRepository: FolderRepository
    private lateinit var folderViewModel: FolderViewModel

    lateinit var folder: Folder
    lateinit var folder2: Folder

    val file1 = MyFile(
        "name 1",
        java.util.Calendar.getInstance(),
        java.util.Calendar.getInstance(),
        0
    )
    val file2 = MyFile(
        "name 2",
        java.util.Calendar.getInstance(),
        java.util.Calendar.getInstance(),
        0
    )
    val file3 = MyFile(
        "name 3",
        java.util.Calendar.getInstance(),
        java.util.Calendar.getInstance(),
        0
    )

    @Before
    fun setUp() {
        file1.creationTime.set(3, 1)
        file2.creationTime.set(3, 2)
        file3.creationTime.set(3, 3)

        file1.lastAccess.set(3, 2)
        file2.lastAccess.set(3, 3)
        file3.lastAccess.set(3, 1)

        file1.numberAccess = 4
        file3.numberAccess = 1

        folder = Folder(
            MutableStateFlow(MutableList(3) {
                when (it) {
                    1 -> file1
                    2 -> file2
                    else -> file3
                }
            }),
            "folder",
            TimeTable()
        )
        folder2 = Folder(
            MutableStateFlow(emptyList<MyFile>().toMutableList()),
            "folder2",
            TimeTable()
        )

        folderRepository = MockFolderRepository(folder)
        folderViewModel = FolderViewModel(folderRepository)
    }


    @Test
    fun addFolderTest() {
        folderViewModel.addFolder(folder2)
        assertEquals(folderViewModel.existingFolders.size, 2)
        assertSame(folderViewModel.existingFolders[0], folder)
        assertSame(folderViewModel.existingFolders[1], folder2)
    }


    @Test
    fun removeFolderTest() {
        folderViewModel.addFolder(folder2)
        folderViewModel.activeFolder = folder

        folderViewModel.deleteFolder(folder)
        assertEquals(folderViewModel.existingFolders.size, 1)
        assertSame(folderViewModel.existingFolders[0], folder2)
        assertNull(folderViewModel.activeFolder)
    }


    @Test
    fun sortFolderTest() {
        folderViewModel.activeFolder = folder

        folderViewModel.sortBy(FilterTypes.NAME)
        assertSame(folder.pdfFiles.value[0], file1)
        assertSame(folder.pdfFiles.value[1], file2)
        assertSame(folder.pdfFiles.value[2], file3)

        folderViewModel.sortBy(FilterTypes.CREATION_UP)
        assertSame(folder.pdfFiles.value[0], file1)
        assertSame(folder.pdfFiles.value[1], file2)
        assertSame(folder.pdfFiles.value[2], file3)

        folderViewModel.sortBy(FilterTypes.CREATION_DOWN)
        assertSame(folder.pdfFiles.value[0], file3)
        assertSame(folder.pdfFiles.value[1], file2)
        assertSame(folder.pdfFiles.value[2], file1)

        folderViewModel.sortBy(FilterTypes.ACCESS_RECENT)
        assertSame(folder.pdfFiles.value[0], file2)
        assertSame(folder.pdfFiles.value[1], file1)
        assertSame(folder.pdfFiles.value[2], file3)

        folderViewModel.sortBy(FilterTypes.ACCESS_OLD)
        assertSame(folder.pdfFiles.value[0], file3)
        assertSame(folder.pdfFiles.value[1], file1)
        assertSame(folder.pdfFiles.value[2], file2)

        folderViewModel.sortBy(FilterTypes.ACCESS_MOST)
        assertSame(folder.pdfFiles.value[0], file1)
        assertSame(folder.pdfFiles.value[1], file3)
        assertSame(folder.pdfFiles.value[2], file2)

        folderViewModel.sortBy(FilterTypes.ACCESS_LEAST)
        assertSame(folder.pdfFiles.value[0], file2)
        assertSame(folder.pdfFiles.value[1], file3)
        assertSame(folder.pdfFiles.value[2], file1)
    }


}


class MockFolderRepository(private val folder: Folder): FolderRepository {
    override fun getFolders(): List<Folder> {
        return List(1) { return@List folder }
    }

}