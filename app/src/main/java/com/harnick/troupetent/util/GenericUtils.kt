package com.harnick.troupetent.util

import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.declaredMemberProperties

object GenericUtils {
  fun Any.setPropertyValue(propertyName: String, value: Any) {
	for (property in this::class.declaredMemberProperties) {
	  if (property.name == propertyName) {
		(property as KMutableProperty<*>).setter.call(this, value)
	  }
	}
  }

  fun isEnum(type: KClass<out Any>): Boolean {
	return type.supertypes.any { t ->
	  (t.classifier as KClass<out Any>).qualifiedName == "kotlin.Enum"
	}
  }
}