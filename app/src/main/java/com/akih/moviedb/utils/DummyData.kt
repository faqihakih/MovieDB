package com.akih.moviedb.utils

import com.akih.moviedb.data.source.local.entity.MovieEntity
import com.akih.moviedb.data.source.local.entity.TVShowEntity

object DummyData {
    fun fetchAllMovie():ArrayList<MovieEntity>{
        val movies = ArrayList<MovieEntity>()
        movies.add(
                MovieEntity(
                        1,
                        "Mortal Kombat",
                        "2021",
                        "1h 50m",
                        "8.1",
                        "Fantasy, Action, Adventure, Science Fiction, Thriller",
                        "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                        "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/2xmx8oPlbDaxTjHsIOZvOt5L3aJ.jpg",
                        "https://youtu.be/NYH2sLid0Zc"
                )
        )
        movies.add(
                MovieEntity(
                        2,
                        "Zack Snyder's Justice League",
                        "2021",
                        "4h 2m",
                        "8.5",
                        "Action, Adventure, Fantasy, Science Fiction",
                        "Determined to ensure Superman's ultimate sacrifice was not in vain, Bruce Wayne aligns forces with Diana Prince with plans to recruit a team of metahumans to protect the world from an approaching threat of catastrophic proportions.",
                        "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg",
                        "https://youtu.be/vM-Bja2Gy04"
                )
        )
        movies.add(
                MovieEntity(
                        3,
                        "Nobody",
                        "2021",
                        "1h 32m",
                        "8.5",
                        "Action, Thriller, Crime",
                        "Hutch Mansell, a suburban dad, overlooked husband, nothing neighbor — a \"nobody.\" When two thieves break into his home one night, Hutch's unknown long-simmering rage is ignited and propels him on a brutal path that will uncover dark secrets he fought to leave behind.",
                        "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/oBgWY00bEFeZ9N25wWVyuQddbAo.jpg",
                        "https://youtu.be/wZti8QKBWPo"
                )
        )
        movies.add(
                MovieEntity(
                        4,
                        "The Marksman",
                        "2021",
                        "1h 48m",
                        "7.1",
                        "Action, Thriller, Crime",
                        "Action, Thriller, Crime",
                        "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6vcDalR50RWa309vBH1NLmG2rjQ.jpg",
                        "https://youtu.be/lEBPNi4bEbc"
                )
        )
        movies.add(
                MovieEntity(
                        5,
                        "Chaos Walking",
                        "2021",
                        "1h 49m",
                        "7.4",
                        "Science Fiction, Action, Adventure, Thriller",
                        "Two unlikely companions embark on a perilous adventure through the badlands of an unexplored planet as they try to escape a dangerous and disorienting reality, where all inner thoughts are seen and heard by everyone.",
                        "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/9kg73Mg8WJKlB9Y2SAJzeDKAnuB.jpg",
                        "https://youtu.be/nRf4ZgzHoVw"
                )
        )
        movies.add(
                MovieEntity(
                        6,
                        "Wonder Woman 1984",
                        "2020",
                        "2h 31m",
                        "6.8",
                        "Fantasy, Action, Adventure",
                        "A botched store robbery places Wonder Woman in a global battle against a powerful and mysterious ancient force that puts her powers in jeopardy.",
                        "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
                        "https://youtu.be/XW2E2Fnh52w"
                )
        )
        movies.add(
                MovieEntity(
                        7,
                        "The Little Things",
                        "2021",
                        "2h 8m",
                        "6.4",
                        "Thriller, Crime",
                        "Deputy Sheriff Joe \"Deke\" Deacon joins forces with Sgt. Jim Baxter to search for a serial killer who's terrorizing Los Angeles. As they track the culprit, Baxter is unaware that the investigation is dredging up echoes of Deke's past, uncovering disturbing secrets that could threaten more than his case.",
                        "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/c7VlGCCgM9GZivKSzBgzuOVxQn7.jpg",
                        "https://youtu.be/1HZAnkxdYuA"
                )
        )
        movies.add(
                MovieEntity(
                        8,
                        "Honest Thief",
                        "2020",
                        "1h 39m",
                        "6.5",
                        "Thriller, Action, Crime, Drama",
                        "A bank robber tries to turn himself in because he's falling in love and wants to live an honest life...but when he realizes the Feds are more corrupt than him, he must fight back to clear his name.",
                        "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/zeD4PabP6099gpE0STWJrJrCBCs.jpg",
                        "https://youtu.be/ZUIEOMOxIDo"
                )
        )
        movies.add(
                MovieEntity(
                        9,
                        "Joker",
                        "2019",
                        "2h 2m",
                        "8.2",
                        "Crime, Thriller, Drama",
                        "During the 1980s, a failed stand-up comedian is driven insane and turns to a life of crime and chaos in Gotham City while becoming an infamous psychopathic crime figure.",
                        "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/udDclJoHjfjb8Ekgsd4FDteOkCU.jpg",
                        "https://youtu.be/zAGVQLHvwOY"
                )
        )
        movies.add(
                MovieEntity(
                        10,
                        "Sherlock Holmes: A Game of Shadows",
                        "2011",
                        "2h 9m",
                        "7.1",
                        "Adventure, Action, Crime, Mystery",
                        "There is a new criminal mastermind at large (Professor Moriarty) and not only is he Holmes’ intellectual equal, but his capacity for evil and lack of conscience may give him an advantage over the detective.",
                        "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/y1MYZkwhZK6L0Jy4YMuPktzDOfn.jpg",
                        "https://youtu.be/V1OYiPa-eLg"
                )
        )
        return movies
    }

    fun fetchAllTVShow() : ArrayList<TVShowEntity>{
        val tvShows = ArrayList<TVShowEntity>()

        tvShows.add(
                TVShowEntity(
                        1,
                        "The Falcon and the Winter Soldier",
                        "2021",
                        "50m",
                        "7.9",
                        "Sci-Fi & Fantasy, Action & Adventure, Drama, War & Politics",
                        "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                        "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                        "https://youtu.be/IWBsDaFWyTE"
                )
        )
        tvShows.add(
                TVShowEntity(
                        2,
                        "The Good Doctor",
                        "2017",
                        "43m",
                        "8.6",
                        "Drama",
                        "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
                        "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                        "https://youtu.be/fYlZDTru55g"
                )
        )
        tvShows.add(
                TVShowEntity(
                        3,
                        "WandaVision",
                        "2021",
                        "36m",
                        "8.4",
                        "Sci-Fi & Fantasy, Mystery, Drama",
                        "Wanda Maximoff and Vision—two super-powered beings living idealized suburban lives—begin to suspect that everything is not as it seems.",
                        "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/glKDfE6btIRcVB5zrjspRIs4r52.jpg",
                        "https://youtu.be/Ch0wMWnXjeA"
                )
        )
        tvShows.add(
                TVShowEntity(
                        4,
                        "The Walking Dead",
                        "2010",
                        "42m",
                        "8.1",
                        "Action & Adventure, Drama, Sci-Fi & Fantasy",
                        "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
                        "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/rqeYMLryjcawh2JeRpCVUDXYM5b.jpg",
                        "https://youtu.be/R1v0uFms68U"
                )
        )
        tvShows.add(
                TVShowEntity(
                        5,
                        "Game of Thrones",
                        "2011",
                        "1h",
                        "8.4",
                        "Sci-Fi & Fantasy, Drama, Action & Adventure",
                        "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                        "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
                        "https://youtu.be/bjqEWgDVPe0"
                )
        )
        tvShows.add(
                TVShowEntity(
                        6,
                        "Marvel Studios: Legends",
                        "2021",
                        "8m",
                        "7.7",
                        "Documentary",
                        "Revisit the epic heroes, villains and moments from across the MCU in preparation for the stories still to come. Each dynamic segment feeds directly into the upcoming series — setting the stage for future events. This series weaves together the many threads that constitute the unparalleled Marvel Cinematic Universe.",
                        "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/EpDuYIK81YtCUT3gH2JDpyj8Qk.jpg",
                        "https://youtu.be/lWQF6F81kbY"
                )
        )
        tvShows.add(
                TVShowEntity(
                        7,
                        "I Am...",
                        "2019",
                        "50m",
                        "5.9",
                        "Drama",
                        "Each hour-long film follows a different women as they experience “moments that are emotionally raw, thought-provoking and utterly personal”.",
                        "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/oogunE22HDTcTxFakKQbwqfw1qo.jpg",
                        "https://youtu.be/yCJhBf-6kug"
                )
        )
        tvShows.add(
                TVShowEntity(
                        8,
                        "Elite",
                        "2018",
                        "1h",
                        "7.9",
                        "Crime, Mystery, Drama",
                        "When three working class kids enroll in the most exclusive school in Spain, the clash between the wealthy and the poor students leads to tragedy.",
                        "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/3NTAbAiao4JLzFQw6YxP1YZppM8.jpg",
                        "https://youtu.be/QNwhAdrdwp0"
                )
        )
        tvShows.add(
                TVShowEntity(
                        9,
                        "Peaky Blinders",
                        "2013",
                        "1h",
                        "8.6",
                        "Crime, Drama",
                        "A gangster family epic set in 1919 Birmingham, England and centered on a gang who sew razor blades in the peaks of their caps, and their fierce boss Tommy Shelby, who means to move up in the world.",
                        "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6PX0r5TRRU5y0jZ70y1OtbLYmoD.jpg",
                        "https://youtu.be/EM12mcTEI88"
                )
        )
        tvShows.add(
                TVShowEntity(
                        10,
                        "Sherlock",
                        "2010",
                        "1h 30m",
                        "8.4",
                        "Crime, Drama, Mystery",
                        "A modern update finds the famous sleuth and his doctor partner solving crime in 21st century London.",
                        "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/7WTsnHkbA0FaG6R9twfFde0I9hl.jpg",
                        "https://youtu.be/xK7S9mrFWL4"
                )
        )
        return tvShows
    }

    fun fetchAllMovieDetail(moviesId: Int): MovieEntity{
        return fetchAllMovie()[0]
    }

    fun fetchAllShowDetail(tvShowId: Int): TVShowEntity{
        return fetchAllTVShow()[0]
    }

    fun fetchAllMovieDetailFavorite(movies: MovieEntity, bookmarked: Boolean): MovieEntity {
        return MovieEntity(
                movies.id,
                movies.title,
                movies.year,
                movies.duration,
                movies.rating,
                movies.genre,
                movies.synopsis,
                movies.banner,
                movies.trailer,
                movies.favorite)
    }

    fun fetchAllShowDetailFavorite(tvShow: TVShowEntity, bookmarked: Boolean): TVShowEntity{
        return TVShowEntity(
                tvShow.id,
                tvShow.title,
                tvShow.year,
                tvShow.duration,
                tvShow.rating,
                tvShow.genre,
                tvShow.synopsis,
                tvShow.banner,
                tvShow.trailer,
                tvShow.favorite
        )
    }
}