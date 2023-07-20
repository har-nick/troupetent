package uk.co.harnick.troupetent.core.account.domain.repository.model

import localstorage.AccountEntity
import uk.co.harnick.bandkit.domain.model.BandcampUser

data class LocalAccount(
    val username: String,
    val nickname: String?,
    val avatarId: Long?,
    val bannerId: Long?,
    val isLastLoggedIn: Boolean
)

fun BandcampUser.toLocalAccount(): LocalAccount = LocalAccount(
    username,
    nickname,
    avatarId,
    bannerId,
    isLastLoggedIn = false
)

fun AccountEntity.toLocalAccount(): LocalAccount = LocalAccount(
    username,
    nickname,
    avatarId,
    bannerId,
    isLastLoggedIn
)
