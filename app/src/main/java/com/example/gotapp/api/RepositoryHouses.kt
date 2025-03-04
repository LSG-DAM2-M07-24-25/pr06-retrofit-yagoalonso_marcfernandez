package com.example.gotapp.api

import com.example.gotapp.model.HouseData

class RepositoryHouses {
    fun getHouses(): List<HouseData> {
        return listOf(
            HouseData(
                id = 1,
                name = "House Baratheon",
                motto = "Ours is the Fury",
                region = "Stormlands",
                castle = "Storm's End",
                imageUrl = "baratheon.webp"
            ),
            HouseData(
                id = 2,
                name = "House Lannister",
                motto = "Hear Me Roar!",
                region = "Westerlands",
                castle = "Casterly Rock",
                imageUrl = "lannister.webp"
            ),
            HouseData(
                id = 3,
                name = "House Stark",
                motto = "Winter is Coming",
                region = "The North",
                castle = "Winterfell",
                imageUrl = "stark.webp"
            ),
            HouseData(
                id = 4,
                name = "House Tully",
                motto = "Family, Duty, Honor",
                region = "Riverlands",
                castle = "Riverrun",
                imageUrl = "tully.webp"
            ),
            HouseData(
                id = 5,
                name = "House Frey",
                motto = "We Stand Together",
                region = "Riverlands",
                castle = "Twins",
                imageUrl = "frey.webp"
            ),
            HouseData(
                id = 6,
                name = "House Tyrell",
                motto = "Growing Strong",
                region = "Reach",
                castle = "Highgarden",
                imageUrl = "tyrell.webp"
            ),
            HouseData(
                id = 7,
                name = "House Hightower",
                motto = "We Light the Way",
                region = "Reach",
                castle = "Oldtown",
                imageUrl = "hightower.webp"
            ),
            HouseData(
                id = 8,
                name = "House Velaryon",
                motto = "The Old, the True, the Brave",
                region = "Crownlands",
                castle = "Castle Driftmark",
                imageUrl = "velaryon.jpg"
            ),
            HouseData(
                id = 9,
                name = "House Targaryen",
                motto = "Fire and Blood",
                region = "Crownlands",
                castle = "Red Keep",
                imageUrl = "targaryen.webp"
            )
        )
    }
} 