package com.deservebetter.ydbandroid.data.firestore

import com.google.firebase.firestore.FirebaseFirestore

object FirestoreCollections {
    const val POSTS = "posts"
    const val COMMENTS = "comments"

    // Helper functions to get collection references
    fun getPostsCollection(firestore: FirebaseFirestore) = firestore.collection(POSTS)
    fun getCommentsCollection(firestore: FirebaseFirestore) = firestore.collection(COMMENTS)
}
