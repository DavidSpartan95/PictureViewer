package com.davidspartan.pictureviewer.model.Realm

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.BsonObjectId
import org.mongodb.kbson.ObjectId

class ImageData: RealmObject {
    @PrimaryKey
    var id: ObjectId = BsonObjectId()
    var name: String = ""
}
