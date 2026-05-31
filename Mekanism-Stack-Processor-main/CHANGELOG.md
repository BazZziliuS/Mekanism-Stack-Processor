# Changelog

All notable changes to this project will be documented in this file.

## [1.2.1] - 2025-02-10

### Fixed
- Fixed crash on startup with 300+ mods caused by premature BlockEntity class loading
  - Replaced `Class.forName()` with `ClassLoader.getResource()` in MoreMachineMixinPlugin to avoid cascading class hierarchy resolution
  - Resolves incompatibility with Collective, Data Anchor, EntityCulling, Sodium, and other mods that add BlockEntity mixins
- Added mixin config priority (1500) to both mixin JSON configs for additional safety

## [1.2.0] - 2025-02-01

### Added
- Support for MoreMachine Advanced Factories (mekaf module):
  - Liquefying Factory
  - Dissolving Factory
  - Oxidizing Factory
  - Crystallizing Factory
  - Washing Factory
  - Centrifuging Factory
  - Chemical Infusing Factory
  - Pressurized Reacting Factory
  - Solar Neutron Activating Factory
- Separate config section for chemical factories (gas/fluid processing)

### Changed
- Mekanism chemical factories (Injecting, Infusing, Purifying) now use chemical_factory_tiers config

## [1.1.0] - 2025-02-01

### Added
- Optional integration with Mekanism:MoreMachine (mekmm)
- Batch processing support for MoreMachine factories:
  - ItemStack to ItemStack Factory (Lathe, Rolling Mill)
  - Recycling Factory
  - Stamping Factory
  - Planting Factory
  - Replicating Factory
- Automatic detection: works without MoreMachine, enables features when present

## [1.0.3] - 2025-01-28

### Changed
- Updated Mekanism dependency to 10.7.17+
- Speed upgrades now properly multiply with batch processing

## [1.0.2] - 2025-01-28

### Fixed
- Fixed speed upgrades breaking batch processing multiplier

## [1.0.1] - 2025-01-28

### Changed
- Added support for newer NeoForge versions (21.1.0+)

## [1.0.0] - 2025-01-28

### Added
- Initial release
- Batch processing for all Mekanism factory types:
  - ItemStack to ItemStack (Crushing, Enriching, Smelting)
  - Combining Factory
  - Chemical Factory (Injecting, Infusing, Purifying)
  - Sawing Factory (Precision Sawmill)
- Batch processing for regular electric machines
- Tiered multipliers based on factory tier:
  - Basic Factory: 8 items per operation
  - Advanced Factory: 16 items per operation
  - Elite Factory: 32 items per operation
  - Ultimate Factory: 64 items per operation (full stack!)
- Regular machines: 4 items per operation
- Fully configurable via `mekanismstackprocessor-common.toml`
