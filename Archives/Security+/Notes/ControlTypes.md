Control Diversity: Using different vendors for different parts of the system

Physical Security Control: Touchable
  - Locks, fences, badges
  - Perimeter Defense: Military Base
  - Buildings
  - Secure Work Areas: Classified
  - Server/Network Rooms

Boundaries
  - Hardware: locked boxes/cabinets
  - Airgapped networks
  
  - Signage: no trespassing

  - Door Locks
    - Limit number of entrances, keep exits for safety
  - Cipher Locks: Numbers
    - Electric: keys, Manual: moving combo
    - Issues: No identification, easy to pwn
  - Proximity Cards: access points
    - Something you have
  - Biometrics
    - Something you are
  - Tailgating
    - Following someone in
    - Stop with mantraps
  - Guards
    - Check badges, Watch cameras, Deterant
  - Cameras
    - Surveillance
    - Rules: only record public areas, notify employees, don't record audio
  - Fencing, Lighting, Alarms
    - Barrier, deters people from entering 
  - Barricades
    - Bollards: prvent cars
  - Hardware Locks
    - Cheap, Vulnerable
  - Cable Locks
    - Thick wire around something, bike lock
  - Locking Cabinets
  - Safes: Important Documents
  - Asset Management
    - Tracking Valuable assets
    - RFID, Serial Numbers, tags
- Environmental Controls
  - HVAC
  - Unit of Measurement: Tonnage = Cooling power
  - Hot/Cool Aisles: Sucks heat 
  - Fire: built in alarms
    - Individual System: individual systems
    - Fixed System: Automatic
  - Shielding
    - Protects from EM or RF transmission
    - Protective cable coating
      - Cat5e, Cat6 (Bound)
    - Protected Cable Distribution: Where you are routing cable
      - Wire Ducts, Troughs
      - Attackers will try to run cables
    - Faraday Cage
      - Room that contains all signals
      - Microwaves

Redundancy 
  - RAID 0, 1, 5, 6, 10
  - RAID 0 = no parity
  - RAID 1 = exact copy
  - RAID 5, 6 = use consecutive drives to rebuild data
    - Takes a long time
  - RAID 10 = Uses 2 sets of raid 1

  - Load Balancing
    - Round Robin: Sends requests to servers in order
    - Affinity: Sends requests from one IP to a server
  
  - Backup types
    - Full Backup: All data
    - Differential Backup: Backs up changed data
    - Incremental: Since last backup
    - Snapshot: saves state
    
    - Don't have a backup until you test it

Business Continuity
  - Continue to run business in unforeseen event
  - Fires, Pandemic, Cyber Attack
  - Business Impact Analysis: BIA, Answers questions
    - Critical Systems
    - Dependencies
    - Downtime Limit
    - Risks
    - Potential Loss

  - Recovery Objectives
    - RTO: Recovery Time Objective: Max time it can be down
    - RPO: Recovery Point Objective: Amount of time where data loss can happen
  
  - Mean Time to Failure
    - MTTF: average on time before it fails
    - MTTR: Mean Time to Recovery: time to recover on accident
  
  - Recovery Sites/ Alternate Sites
    - Hot Site: Always on
    - Cold Site: Bare building, Site with only power and internet
    - Warm Site: Between hot and cold

  - Disaster Recovery
    - Priority list of critical system
    - Most critical leaves first and returns last

  - Testing
    - Tabletop: talk through it
    - Functional exercise: actually go through it

  - Privacy Threshold and impact
    - Assess PII potential or actual loss


    
    


