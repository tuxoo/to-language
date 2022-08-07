package com.tolanguage.config.security

import com.tolanguage.model.dto.UserDto
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

class AppUserDetails(
    private val id: UUID,
    private val login: String,
    private val isEnabled: Boolean,
    private val grantedAuthorities: MutableCollection<out GrantedAuthority>
) : UserDetails {

    companion object {
        fun toAppUserDetails(user: UserDto) =
            with(user)
            {
                AppUserDetails(
                    id = id,
                    login = loginEmail,
                    isEnabled = isEnabled,
                    grantedAuthorities = mutableListOf(SimpleGrantedAuthority(user.role.name))
                )
            }
    }

    fun getId(): UUID = id

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = grantedAuthorities

    override fun getPassword(): String = ""

    override fun getUsername(): String = login

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = isEnabled
}