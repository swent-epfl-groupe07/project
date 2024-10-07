package com.github.se.project.model.folder

interface FolderRepository {
  fun getFolders(): List<Folder>
}
