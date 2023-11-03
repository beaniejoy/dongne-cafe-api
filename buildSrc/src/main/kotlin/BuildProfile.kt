import java.lang.IllegalArgumentException

class BuildProfile private constructor(
    private val activeProfile: Profile
) {
    companion object {
        fun init(): BuildProfile {
            val activeProfile = System.getProperty("profile")?.let {
                Profile.of(it)
            } ?: Profile.NONE

            return BuildProfile(activeProfile)
        }
    }

    fun isDev(): Boolean {
        return activeProfile == Profile.LOCAL
    }

    fun isProd(): Boolean {
        return activeProfile == Profile.PROD
    }

    fun getProfileName(): String {
        return activeProfile.profileName
    }

    enum class Profile(val profileName: String) {
        NONE("none"),
        LOCAL("local"),
        PROD("prod")
        ;

        companion object {
            fun of(profileName: String): Profile {
                return Profile.values().find {
                    it.profileName == profileName
                } ?: throw IllegalArgumentException("invalid profile name")
            }
        }
    }
}
