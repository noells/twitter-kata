package kata.twitter.core.use.cases

interface FollowUserUserCase {
    fun followUser(follower: String, followed: String)
}