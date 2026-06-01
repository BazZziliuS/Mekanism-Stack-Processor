## [1.3.0] - 2026-06-01

### Added
- Support for Evolved Mekanism (evolvedmekanism) — optional integration:
  - Alloying Factory (`TileEntityAlloyingFactory`) — batch processing with factory tier multiplier
  - Alloyer machine — uses regular machine multiplier
  - Chemixer machine — uses regular machine multiplier
  - Melter machine — uses regular machine multiplier
  - Solidifier machine — uses regular machine multiplier
- New config section `[evolved_mekanism_factory_tiers]` with multipliers for extended factory tiers:
  - Overclocked, Quantum, Dense, Multiversal, Creative (default: 64 each)

### Fixed
- `MatchException` crash when Evolved Mekanism is installed: the extended `FactoryTier` values
  (OVERCLOCKED, QUANTUM, DENSE, MULTIVERSAL, CREATIVE) injected by EM into the Mekanism enum
  now have a proper `default` fallback in `getFactoryMultiplier` and `getChemicalFactoryMultiplier`
