package com.harnick.troupetent.library.data.dto.collection_items

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// NOTES:
// Assuming dateAdded and datePurchased are different in case of preorders
// Featured track info defaults to first track if no featured track is selected
// itemArtUrl is used over itemArtEntity for granular cover sizing

// Can items have multiple albums?
// Is merchSnapshot limited to 4 values?
// Does isPreorder change on release?

// Some nullable values' data types couldn't be inferred

// TODO: Figure out how @Transient works

@Serializable
data class BandcampCollectionItemEntity(
	@SerialName("added")
	val dateAdded: String,
	@SerialName("album_id")
	val albumId: Long?,
	@SerialName("album_title")
	val albumTitle: String?,
	@SerialName("also_collected_count")
	val albumPurchaseCount: Int,
	@SerialName("band_id")
	val bandId: Long,
	@SerialName("band_image_id")
	val bandImageId: Long?,
	@SerialName("band_location")
	val bandLocation: String?,
	@SerialName("band_name")
	val bandName: String,
	@SerialName("band_url")
	val bandUrl: String,
	val currency: String,
	val discount: Boolean?,
	@SerialName("download_available")
	val downloadAvailable: Boolean,
	@SerialName("fan_id")
	val fanId: Long,
	@SerialName("featured_track")
	val featuredTrack: Long,
	@SerialName("featured_track_duration")
	val featuredTrackDuration: Double,
	@SerialName("featured_track_encodings_id")
	val featuredTrackEncodingId: Long,
	@SerialName("featured_track_is_custom")
	val featuredTrackIsCustom: Boolean,
	@SerialName("featured_track_license_id")
	val featuredTrackLicenseId: Long?,
	@SerialName("featured_track_number")
	val featuredTrackNumber: Int?,
	@SerialName("featured_track_title")
	val featuredTrackTitle: String,
	@SerialName("featured_track_url")
	val featuredTrackUrl: String?,
	@SerialName("genre_id")
	val genreId: Long?,
	@SerialName("gift_id")
	val giftId: Long?,
	@SerialName("gift_recipient_name")
	val giftRecipientName: String?,
	@SerialName("gift_sender_name")
	val giftSenderName: String?,
	@SerialName("gift_sender_note")
	val giftSenderNote: String?,
	@SerialName("has_digital_download")
	val hasDigitalDownload: Boolean?,
	@SerialName("hidden")
	val isHidden: Boolean?,
	val index: Int?,                                                                                  // Seems to be index of album in collection
	@SerialName("is_giftable")
	val isGiftable: Boolean,
	@SerialName("is_preorder")
	val isPreorder: Boolean,
	@SerialName("is_private")
	val isPrivate: Boolean,                                                                           // Not to be confused with isHidden - True if artist hides their album from purchases
	@SerialName("is_purchasable")
	val isPurchasable: Boolean,
	@SerialName("is_set_price")
	val isSetPrice: Boolean,
	@SerialName("is_subscriber_only")
	val isSubscriberOnly: Boolean,
	@SerialName("is_subscription_item")
	val isSubscriptionItem: Boolean,
	@SerialName("item_art")
	val itemArtEntity: ItemArtEntity,
	@SerialName("item_art_id")
	val itemArtId: Long,
	@SerialName("item_art_ids")
	val itemArtIds: Long?,
	@SerialName("item_art_url")
	val itemArtUrl: String,
	@SerialName("item_id")
	val itemId: Long,
	@SerialName("item_title")
	val itemTitle: String,
	@SerialName("item_type")
	val itemType: String,
	@SerialName("item_url")
	val itemUrl: String,
	@SerialName("label")
	val labelName: String?,
	@SerialName("label_id")
	val labelId: Long?,
	@SerialName("licensed_item")
	val licensedItem: Boolean?,
	@SerialName("listen_in_app_url")
	val listenInAppUrl: String,
	@SerialName("merch_ids")
	val merchIds: List<Long>?,
	@SerialName("merch_snapshot")
	val merchSnapshotEntity: MerchSnapshotEntity?,
	@SerialName("merch_sold_out")
	val isMerchSoldOut: Boolean?,
	@SerialName("message_count")
	val messageCount: Long?,
	@SerialName("num_streamable_tracks")
	val streamableTrackCount: Int,
	@SerialName("package_details")
	val packageDetailsEntity: PackageDetailsEntity?,
	@SerialName("price")
	val itemPrice: Double,
	@SerialName("purchased")
	val datePurchased: String,
	@SerialName("release_count")
	val releaseCount: Int?,
	val releases: Int?,
	@SerialName("require_email")
	val isEmailRequired: Boolean?,
	@SerialName("sale_item_id")
	val itemPurchaseId: Long,
	@SerialName("sale_item_type")
	val purchasedItemType: String,
	@SerialName("service_name")
	val serviceName: String?,
	@SerialName("service_url_fragment")
	val serviceUrlFragment: String?,
	val token: String,
	@SerialName("tralbum_id")
	val trAlbumId: Long,
	@SerialName("tralbum_type")
	val trAlbumType: String,
	@SerialName("updated")
	val dateUpdated: String,
	@SerialName("url_hints")
	val urlHints: UrlHintsEntity,
	@SerialName("variant_id")
	val variantId: Long?,
	val why: String?                                                                                  // I ask myself that every day
)