package jvsk.kotlin

fun a(key: Key) {
    findEntityByKey(key)?.action()
    externalAction(findEntityByKey(key)!!)
    findEntityByKey(key)?.let { externalAction(it) }
    val entity1 = findEntityByKey(key) ?: defaultEntity()
    val entity2 = findEntityByKey(key) ?: return
}

fun b(boolean: Boolean) {
    boolean || return
}

class Key

class Entity {
    fun action() {}
}

fun externalAction(entity: Entity) {

}

external fun findEntityByKey(key: Key): Entity?

external fun defaultEntity(): Entity
