# Mekanism Stack Processor

[![Build](https://github.com/BazZziliuS/Mekanism-Stack-Processor/actions/workflows/build.yml/badge.svg)](https://github.com/BazZziliuS/Mekanism-Stack-Processor/actions/workflows/build.yml)
[![Minecraft](https://img.shields.io/badge/Minecraft-1.21.1-green.svg)](https://minecraft.net/)
[![NeoForge](https://img.shields.io/badge/NeoForge-21.1.218-orange.svg)](https://neoforged.net/)
[![Mekanism](https://img.shields.io/badge/Mekanism-10.7.17+-blue.svg)](https://github.com/mekanism/Mekanism)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

[![Modrinth](https://img.shields.io/modrinth/dt/mekanism-stack-processor?logo=modrinth&label=Modrinth&color=00AF5C)](https://modrinth.com/mod/mekanism-stack-processor)
[![CurseForge](https://cf.way2muchnoise.eu/mekanism-stack-processor.svg)](https://www.curseforge.com/minecraft/mc-mods/mekanism-stack-processor)

A NeoForge mod that adds **batch processing** to Mekanism machines. Instead of processing items one at a time, machines process multiple items per operation based on their tier.

## Features

- **Tiered batch processing** - Higher tier factories process more items at once
- **Configurable multipliers** - Adjust items per operation for each tier
- **Works with all factory types** - Crushing, Enriching, Smelting, Combining, Sawing, Injecting, Infusing, Purifying
- **Regular machines support** - Non-factory machines also get batch processing
- **Optional MoreMachine integration** - Full support for Mekanism:MoreMachine mod

## Supported Machines

### Mekanism (Required)
| Factory Type | Config Section |
|--------------|----------------|
| Crushing, Enriching, Smelting | `factory_tiers` |
| Combining Factory | `factory_tiers` |
| Sawing Factory | `factory_tiers` |
| Injecting, Infusing, Purifying | `chemical_factory_tiers` |
| Regular Machines | `regular_machines` |

### Mekanism:MoreMachine (Optional)
If [Mekanism:MoreMachine](https://www.curseforge.com/minecraft/mc-mods/mekanism-more-machine) is installed, the following factories are also supported:

**mekmm module** (uses `factory_tiers`):
- Lathe, Rolling Mill, Recycling, Stamping, Planting, Replicating factories

**mekaf module** (uses `chemical_factory_tiers`):
- Liquefying, Dissolving, Oxidizing, Crystallizing, Washing
- Centrifuging, Chemical Infusing, Pressurized Reacting
- Solar Neutron Activating factories

## Default Configuration

### Item Factories (`factory_tiers`)
| Tier | Items per Operation |
|------|---------------------|
| Basic | 8 |
| Advanced | 16 |
| Elite | 32 |
| Ultimate | 64 |

### Chemical Factories (`chemical_factory_tiers`)
| Tier | Operations Multiplier |
|------|----------------------|
| Basic | 1 |
| Advanced | 4 |
| Elite | 8 |
| Ultimate | 16 |

### Regular Machines
| Type | Items per Operation |
|------|---------------------|
| Non-factory machines | 4 |

## Requirements

- Minecraft 1.21.1
- NeoForge 21.1.0+
- Mekanism 10.7.17+
- Mekanism:MoreMachine (optional)

## Installation

1. Download the latest release from [Releases](https://github.com/BazZziliuS/Mekanism-Stack-Processor/releases)
2. Place the JAR file in your `mods` folder
3. Make sure Mekanism is installed
4. (Optional) Install Mekanism:MoreMachine for extended support
5. Launch the game!

## Configuration

Configuration file is located at:
```
config/mekanismstackprocessor-common.toml
```

### General
- `enableOptimization` - Enable/disable the mod

### Factory Tiers (item processing)
- `basicTierMultiplier` - Items per operation for Basic Factory
- `advancedTierMultiplier` - Items per operation for Advanced Factory
- `eliteTierMultiplier` - Items per operation for Elite Factory
- `ultimateTierMultiplier` - Items per operation for Ultimate Factory

### Chemical Factory Tiers (gas/fluid processing)
- `chemBasicTierMultiplier` - Multiplier for Basic Chemical Factory
- `chemAdvancedTierMultiplier` - Multiplier for Advanced Chemical Factory
- `chemEliteTierMultiplier` - Multiplier for Elite Chemical Factory
- `chemUltimateTierMultiplier` - Multiplier for Ultimate Chemical Factory

### Regular Machines
- `baseMachineMultiplier` - Items per operation for regular machines

## Building from Source

```bash
git clone https://github.com/BazZziliuS/Mekanism-Stack-Processor.git
cd Mekanism-Stack-Processor
./gradlew build
```

The built JAR will be in `build/libs/`.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Credits

- **Author:** BazZziliuS
- **Mekanism Team** for the amazing tech mod
- **NeoForge Team** for the modding platform
- **MoreMachine Team** for the extended machines
