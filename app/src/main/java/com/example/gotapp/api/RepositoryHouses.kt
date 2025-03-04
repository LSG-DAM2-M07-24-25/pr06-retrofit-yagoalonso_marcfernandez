package com.example.gotapp.api

import com.example.gotapp.model.HouseData
import com.example.gotapp.R

class RepositoryHouses {
    fun getHouses(): List<HouseData> {
        return listOf(
            HouseData(
                id = 1,
                name = "House Baratheon",
                motto = "Ours is the Fury",
                region = "Stormlands",
                castle = "Storm's End",
                imageUrl = "drawable/baratheon"
            ),
            HouseData(
                id = 2,
                name = "House Lannister",
                motto = "Hear Me Roar!",
                region = "Westerlands",
                castle = "Casterly Rock",
                imageUrl = "drawable/lannister"
            ),
            HouseData(
                id = 3,
                name = "House Stark",
                motto = "Winter is Coming",
                region = "The North",
                castle = "Winterfell",
                imageUrl = "drawable/stark"
            ),
            HouseData(
                id = 4,
                name = "House Tully",
                motto = "Family, Duty, Honor",
                region = "Riverlands",
                castle = "Riverrun",
                imageUrl = "drawable/tully"
            ),
            HouseData(
                id = 5,
                name = "House Frey",
                motto = "We Stand Together",
                region = "Riverlands",
                castle = "Twins",
                imageUrl = "drawable/frey"
            ),
            HouseData(
                id = 6,
                name = "House Tyrell",
                motto = "Growing Strong",
                region = "Reach",
                castle = "Highgarden",
                imageUrl = "drawable/tyrell"
            ),
            HouseData(
                id = 7,
                name = "House Hightower",
                motto = "We Light the Way",
                region = "Reach",
                castle = "Oldtown",
                imageUrl = "drawable/hightower"
            ),
            HouseData(
                id = 8,
                name = "House Velaryon",
                motto = "The Old, the True, the Brave",
                region = "Crownlands",
                castle = "Castle Driftmark",
                imageUrl = "drawable/velaryon"
            ),
            HouseData(
                id = 9,
                name = "House Targaryen",
                motto = "Fire and Blood",
                region = "Crownlands",
                castle = "Red Keep",
                imageUrl = "drawable/targaryen"
            ),
            HouseData(
                id = 10,
                name = "House Greyjoy",
                motto = "We Do Not Sow",
                region = "Iron Islands",
                castle = "Iron Islands",
                imageUrl = "drawable/greyjoy"
            ),
            HouseData(
                id = 11,
                name = "House Martell",
                motto = "Unbowed, Unbent, Unbroken",
                region = "Dorne",
                castle = "Sunspear",
                imageUrl = "drawable/martell"
            ),
            HouseData(
                id = 12,
                name = "House Baelish",
                motto = "Knowledge is Power",
                region = "Vale of Arryn",
                castle = "Harrenhal",
                imageUrl = "drawable/baelish"
            ),
            HouseData(
                id = 13,
                name = "House Arryn",
                motto = "As High as Honor",
                region = "Vale of Arryn",
                castle = "Eyrie",
                imageUrl = "drawable/arryn"
            ),
            HouseData(
                id = 14,
                name = "House Tarly",
                motto = "First in Battle",
                region = "Reach",
                castle = "Horn Hill",
                imageUrl = "drawable/tarly"
            ),
            HouseData(
                id = 15,
                name = "House Bolton",
                motto = "Our Blades Are Sharp",
                region = "North",
                castle = "Dreadfort",
                imageUrl = "drawable/bolton"
            ),
            HouseData(
                id = 16,
                name = "House Mallister",
                motto = "Above the Rest",
                region = "Riverlands",
                castle = "Seagard",
                imageUrl = "drawable/mallister"
            ),
            HouseData(
                id = 17,
                name = "House Mormont",
                motto = "Here We Stand",
                region = "North",
                castle = "Mormont Keep",
                imageUrl = "drawable/mormont"
            ),
            HouseData(
                id = 18,
                name = "House Royce",
                motto = "We Remember",
                region = "Vale of Arryn",
                castle = "Runestone",
                imageUrl = "drawable/royce"
            ),
            HouseData(
                id = 19,
                name = "House Karstark",
                motto = "The Winter Sun",
                region = "North",
                castle = "Karhold",
                imageUrl = "drawable/karstark"
            ),
            HouseData(
                id = 20,
                name = "House Allyrion",
                motto = "No Enemy will Pass",
                region = "Dorne",
                castle = "Bondadivina",
                imageUrl = "drawable/allyrion"
            )
        )
    }
} 