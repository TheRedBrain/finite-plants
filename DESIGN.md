# Design

## Saplings

- more growth stages
- growing takes longer
- generated feature depends on environment (light, temperature, maybe other world gen noise types OR simply biomes)
- bone meal does not accelerate growth

## Sugar Cane Roots

- sugar cane can only be placed on this block
- sugar cane feature generates this block as the first one, then normal sugar cane
- maybe not instant break
- initially finite (maybe a high tier trading item)

## Cactus Roots

- cacti can only be placed on this block
- cactus feature generates this block as the first one, then normal cactus (flower)ww
- initially finite (maybe a high tier trading item)

## Sweet Berry Bushes

the "minecraft:sweet_berries" item can no longer be eaten, and it is only dropped when breaking a sweet berry bush (always 1, regardless of growth status)
    - rename the item to sweet berry bush seed/sapling
    - new item model
harvesting a sweet berry bush now drops a custom item that takes the copies the food properties of the vanilla sweet berry item
    - this item can't be used to plant new sweet berry bushes

## Crops

- add seeds for potatoes, carrots and Farmers Delight crops
  - vanilla blocks are renamed into seed-potato/carrot/etc and drop new item
  - new crop blocks use new items as block items and drop vanilla item
- seeds should only be acquirable in villages (no zombies dropping crops or wheat seeds from grass)
- growing takes longer
- crops require watered farmland
- crops might require sky access
- crops might only grow in certain biomes
- bone meal doesn't accelerate growth

crop blocks can be right-clicked when fully-grown (possible tool requirement), to harvest them. This will not break them, but set them back to their first stage.
their fruits are different items, which are the regular food
their block items are quite rare, every crop block drops their block item only once (probably regardless of growth stage)
    initial sources of crops are village farms, maybe sunken ships (rarely), the food items are more common
    wild crops from farmers delight drop the food items and very rarely the seed



## Farmland

- can be fertilized using bone meal -> faster crop growth