// Create
CREATE

// Nós principais
(Worth1:Worth_1 {name: 'Worth1'}),

(GreenBeer1_out:Green_Beer_1 {name: 'GreenBeer1'}),
(GreenBeer1_cent:Green_Beer_1 {name: 'GreenBeer1'})

(Filtration1:Filtration_1 {name: 'Filtration1'}),

// Valvulas Mixproof Worth1
(Vm1Worth1:Valvula_Mixproof_Worth_1 {name: 'Vm1Worth1'}),
(Vm2Worth1:Valvula_Mixproof_Worth_1 {name: 'Vm2Worth1'}),
(Vm3Worth1:Valvula_Mixproof_Worth_1 {name: 'Vm3Worth1'}),

// Valvulas Solenoide Worth1
(Vs1Worth1:Valvula_Solenoide_Worth_1 {name: 'Vs1Worth1'}),
(Vs2Worth1:Valvula_Solenoide_Worth_1 {name: 'Vs2Worth1'}),

// Valvulas Mixproof Green beer 1
(Vm1GreenBeer1:Valvula_Mixproof_Green_Beer_1 {name: 'Vm1GreenBeer1'}),
(Vm2GreenBeer1:Valvula_Mixproof_Green_Beer_1 {name: 'Vm2GreenBeer1'}),


// Valvulas Solenoide Green beer 1
(Vs1GreenBeer1:Valvula_Solenoide_Green_Beer_1 {name: 'Vs1GreenBeer1'}),

// Valvulas Mixproof Filtration 1
(Vm1Filtration1:Valvula_Mixproof_Filtration_1 {name: 'Vm1Filtration1'}),
(Vm2Filtration1:Valvula_Mixproof_Filtration_1 {name: 'Vm2Filtration1'}),

// Valvulas Solenoide Filtration 1
(Vs1Filtration1:Valvula_Solenoide_Filtration_1 {name: 'Vs1Filtration1'}),

// Tanques
(CCT1:Tanque {name: 'CCT1'}),
(CCT16:Tanque {name: 'CCT16'}),

// Valvulas Mixproof Tanques Inferiores
(VmCCT1:Valvula_Mixproof_Tanques {name: 'VmCCT1'}),
(VmCCT16:Valvula_Mixproof_Tanques {name: 'VmCCT16'}),

// Ligações Worth 1
(Vm1Worth1)-[:Tubulação_1 {tempo: 0.5}]->(Vs1Worth1),
(Vs1Worth1)-[:Tubulação_1 {tempo: 0.5}]->(Vm1Worth1),

(Vs1Worth1)-[:Tubulação_2 {tempo: 0.5}]->(Vm2Worth1),
(Vm2Worth1)-[:Tubulação_2 {tempo: 0.5}]->(Vs1Worth1),

(Vm2Worth1)-[:Tubulação_3 {tempo: 0.5}]->(Vm3Worth1),
(Vm3Worth1)-[:Tubulação_3 {tempo: 0.5}]->(Vm2Worth1),

(Vm3Worth1)-[:Tubulação_4 {tempo: 0.5}]->(Vs2Worth1),
(Vs2Worth1)-[:Tubulação_4 {tempo: 0.5}]->(Vm3Worth1),

// Ligação interna hub 1
// linha 1
(Vm2Worth1)-[:Tubulação_69 {tempo: 0.5}]->(Vm1GreenBeer1),
(Vm1GreenBeer1)-[:Tubulação_69 {tempo: 0.5}]->(Vm2Worth1),

(Vm1Filtration1)-[:Tubulação_70 {tempo: 0.5}]->(Vm1GreenBeer1),
(Vm1GreenBeer1)-[:Tubulação_70 {tempo: 0.5}]->(Vm1Filtration1),

// linha 2
(Vm3Worth1)-[:Tubulação_71 {tempo: 0.5}]->(Vm2GreenBeer1),
(Vm2GreenBeer1)-[:Tubulação_71 {tempo: 0.5}]->(Vm3Worth1),

(Vm2Filtration1)-[:Tubulação_72 {tempo: 0.5}]->(Vm2GreenBeer1),
(Vm2GreenBeer1)-[:Tubulação_72 {tempo: 0.5}]->(Vm2Filtration1),

// Ligação hub 1 para tanques 1
(Vm2Worth1)-[:Tubulação_153 {tempo: 0.5}]->(VmCCT1),
(VmCCT1)-[:Tubulação_153 {tempo: 0.5}]->(Vm2Worth1),

// Ligação hub 1 para tanques 16
(Vm3Worth1)-[:Tubulação_154 {tempo: 0.5}]->(VmCCT16),
(VmCCT16)-[:Tubulação_154 {tempo: 0.5}]->(Vm3Worth1),

// Ligações Green Beer 1
(Vm1GreenBeer1)-[:Tubulação_173 {tempo: 0.5}]->(Vm2GreenBeer1),
(Vm2GreenBeer1)-[:Tubulação_173 {tempo: 0.5}]->(Vm1GreenBeer1),

(Vm2GreenBeer1)-[:Tubulação_174 {tempo: 0.5}]->(Vs1GreenBeer1),
(Vs1GreenBeer1)-[:Tubulação_174 {tempo: 0.5}]->(Vm2GreenBeer1),

// Ligações Filtration 1
(Vm1Filtration1)-[:Tubulação_175 {tempo: 0.5}]->(Vm2Filtration1),
(Vm2Filtration1)-[:Tubulação_175 {tempo: 0.5}]->(Vm1Filtration1),

(Vm2Filtration1)-[:Tubulação_176 {tempo: 0.5}]->(Vs1Filtration1),
(Vs1Filtration1)-[:Tubulação_176 {tempo: 0.5}]->(Vm2Filtration1),

// Ligação aos nós principais
(Worth1)-[:Tubulação_177 {tempo: 0.5}]->(Vm1Worth1),

(Vm1GreenBeer1)-[:Tubulação_178 {tempo: 0.5}]->(GreenBeer1_out),

(GreenBeer1_cent)-[:Tubulação_178 {tempo: 0.5}]->(Vm2GreenBeer1),

(Vm1Filtration1)-[:Tubulação_179 {tempo: 0.5}]->(Filtration1),

// Ligação aos tanques
(VmCCT1)-[:Tubulação_180 {tempo: 0.5}]->(CCT1),
(CCT1)-[:Tubulação_180 {tempo: 0.5}]->(VmCCT1),

(VmCCT16)-[:Tubulação_181 {tempo: 0.5}]->(CCT16),
(CCT16)-[:Tubulação_181 {tempo: 0.5}]->(VmCCT16)

RETURN *