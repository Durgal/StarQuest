
# Star Quest

## Overview

Star Quest is a personal research project exploring the relationship between deterministic simulation systems and language models.

The project began as a text adventure engine, but gradually evolved into an investigation of a broader question:

> Can language models create better interactive worlds when they describe reality rather than simulate it?

Most AI-powered games ask a language model to maintain world state, interpret player actions, simulate consequences, and generate narrative simultaneously.

Star Quest takes a different approach.

The simulation is the source of truth. World state, entities, locations, and interactions are represented and updated through deterministic code. Language models are treated as interfaces that interpret or narrate events rather than define them.

The project serves as a sandbox for experimenting with:

-   Procedural world generation
    
-   Simulation-driven storytelling
    
-   Symbolic world modeling
    
-   Natural language interfaces
    
-   Emergent behavior
    
-   Human-AI interaction
    

----------

## Research Question

The central question behind Star Quest is:

> What role should language models play in interactive worlds?

My current hypothesis is that language models are often most effective when they operate on top of a simulation rather than replace one.

Instead of asking a LLM to maintain reality, a simulation can provide a stable world model while the LLM focuses on tasks where flexibility and creativity are valuable:

-   Narration
    
-   Description
    
-   Interpretation
    
-   Communication
    

Star Quest exists primarily to explore that hypothesis.

----------

## Current State

Star Quest is an active prototype rather than a complete game.

Implemented and experimental systems include:

-   Entity generation
    
-   Procedural world-building concepts
    
-   Species and civilization modeling
    
-   Occupations and attributes
    
-   Noun/adjective-based world representation
    
-   Interaction systems
    
-   Parser experiments
    
-   Swing-based UI components
    
-   Early natural language integration experiments
    

Many systems remain exploratory and are frequently redesigned as new ideas emerge.

----------

## Core Concepts

### Everything is a Noun

The fundamental building block in Star Quest is a **Noun**.

A noun may represent:

-   A player
    
-   A creature
    
-   A weapon
    
-   A settlement
    
-   A resource
    
-   A civilization
    
-   A planet
    

Rather than building deep inheritance hierarchies, the project treats entities as composable objects that can acquire properties and behaviors.

This approach is intended to support procedural generation while keeping the world model flexible.

----------

### Adjectives Represent State

State is represented through adjectives attached to nouns.

For example:

```text
Sword
 ├── Sharp
 ├── Metal
 └── Rusted

Traveler
 ├── Hungry
 ├── Injured
 └── Armed

```

The goal is to make it easier for systems to generate and modify entities dynamically without requiring specialized classes for every possible variation.

----------

### Simulation First

A guiding principle that emerged during development is that simulation and narration should remain separate concerns.

Instead of:

```text
Player Input
      │
      ▼
     LLM
      │
      ▼
 World State

```

Star Quest aims toward:

```text
Simulation
      │
      ▼
 World State
      │
      ▼
 Significant Events
      │
      ▼
    Narrator
      │
      ▼
 Player Experience

```

In this model:

-   The simulation determines what happens.
    
-   The narrator explains what happened.
    

----------

## Procedural Generation

A long-term goal of the project is to generate worlds rather than author them manually.

Areas of experimentation include:

-   Creatures
    
-   Settlements
    
-   Resources
    
-   Factions
    
-   Relationships
    
-   Environmental features
    

The objective is not simply variety, but the creation of systems capable of producing interesting interactions over time.

----------

## Emergent Behavior

Much of the project's design is motivated by emergence.

Rather than scripting events directly, the simulation should allow larger patterns to arise from interactions between entities and systems.

Potential examples include:

-   Resource shortages
    
-   Population shifts
    
-   Territorial disputes
    
-   Economic changes
    
-   Discovery of rare resources
    
-   Cascading chains of interactions
    

The interesting part is often not the event itself, but understanding why it happened.

----------

## Natural Language Experiments

One of the earliest goals was allowing players to interact using natural language.

Example commands:

```text
Pick up the rusty sword.

Travel north and inspect the tower.

Trade with the merchant.
```

Several prototypes experimented with using language models to translate player intent into simulation actions.

Some of this work remains in the codebase as parser experiments and partially implemented systems.

----------

## What Didn't Work

Some of the most valuable lessons came from approaches that initially seemed promising.

Early prototypes relied more heavily on language models for gameplay logic and command interpretation.

These experiments tended to produce unexpected results, and introduced persistent engineering challenges:

-   Ambiguous command interpretation
    
-   Non-deterministic outcomes
    
-   Difficult debugging
    
-   State drift
    
-   Hallucinated entities and actions
    
-   Reduced confidence in simulation correctness
    

These failures ultimately led to the simulation-first architecture that currently guides the project.

One of the primary conclusions so far is that language models are often easier to integrate when they operate on established world state rather than create or maintain that state themselves.

----------

## Future Directions

The most ambitious component of Star Quest has not yet been implemented.

The long-term vision is an AI narrator that receives structured information about the simulation:

-   Location data
    
-   Nearby entities
    
-   Recent events
    
-   Environmental context
    
-   Significant state changes
    

The narrator would then transform that information into natural language descriptions.

For example, a simulation might report:

```text
Merchant caravan arrived.
Food supply decreased.
Traveler injured.
```

A narrator could communicate the same information as:

> The market square feels unusually crowded today. A newly arrived caravan has drawn merchants from across the region, while a wounded traveler rests near the fountain as townsfolk quietly discuss shortages in the surrounding countryside.

The simulation remains authoritative. The language model becomes a storyteller.

----------

## Lessons Learned

A recurring lesson throughout development has been that reliability and creativity often benefit from different systems.

Deterministic code is well-suited for:

-   World state
    
-   Rules
    
-   Simulation
    
-   Consistency
    

Language models are well-suited for:

-   Description
    
-   Communication
    
-   Interpretation
    
-   Narrative presentation
    

Star Quest explores what happens when those responsibilities are separated rather than combined.

----------

## Why This Project Exists

Star Quest started as a hobby project and became an ongoing exploration of simulation systems, emergent behavior, and AI-assisted storytelling.

More broadly, it is an attempt to understand how symbolic systems and language models can complement one another without requiring either to do everything.

Whether the end result becomes a game, a simulation framework, or simply a collection of useful experiments remains an open question.

The research process itself is the point.

----------

## Technologies

-   Java
    
-   Swing
    
-   Object-Oriented Design
    
-   Procedural Generation
    
-   Natural Language Processing Experiments
    
-   Simulation Systems
    

----------

## Author

Christopher Granat

Senior Software Engineer interested in simulation systems, emergent behavior, reinforcement learning environments, procedural generation, and the role of language models in interactive worlds.