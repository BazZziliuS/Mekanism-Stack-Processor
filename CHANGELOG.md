# Changelog

All notable changes to this project will be documented in this file.

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
