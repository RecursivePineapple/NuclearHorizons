#!/usr/bin/python3

import re
from pathlib import Path
from os import path

GT5u="../GT5-Unofficial"

gtStyle=[
    ("src/main/java/gregtech/api/enums/Materials.java", r"public static Materials (\w+);"),
]

bwStyle=[
    ("src/main/java/bartworks/system/material/WerkstoffLoader.java", r"public static final Werkstoff (\w+) ="),
    ("src/main/java/gtnhlanth/common/register/WerkstoffMaterialPool.java", r"public static final Werkstoff (\w+) ="),
    ("src/main/java/goodgenerator/items/GGMaterial.java", r"public static final Werkstoff (\w+) = "),
    ("src/main/java/gtnhlanth/common/register/BotWerkstoffMaterialPool.java", r"public static final Werkstoff (\w+) = "),

    ("src/main/java/com/recursive_pineapple/nuclear_horizons/reactors/items/material/MaterialsNuclear.java", r"public static final Werkstoff (\w+) = "),
    ("src/main/java/com/recursive_pineapple/nuclear_horizons/reactors/items/material/MaterialsChemical.java", r"public static final Werkstoff (\w+) = "),
]

gtppStyle=[
    ("src/main/java/gtPlusPlus/core/material/MaterialMisc.java", r"public static final Material (\w+) = "),
    ("src/main/java/gtPlusPlus/core/material/MaterialsAlloy.java", r"public static final Material (\w+) = "),
    ("src/main/java/gtPlusPlus/core/material/nuclear/MaterialsFluorides.java", r"public static final Material (\w+) = "),
    ("src/main/java/gtPlusPlus/core/material/MaterialsElements.java",  r"public final Material (\w+) = "),
    ("src/main/java/gtPlusPlus/core/material/nuclear/MaterialsNuclides.java", r"public static final Material (\w+) = "),
]

out=[
    "package com.recursive_pineapple.nuclear_horizons.recipes;",
    "",
    "import bartworks.system.material.WerkstoffLoader;",
    "import com.recursive_pineapple.nuclear_horizons.reactors.items.material.MaterialsNuclear;",
    "import com.recursive_pineapple.nuclear_horizons.reactors.items.material.MaterialsChemical;",
    "import goodgenerator.items.GGMaterial;",
    "import gregtech.api.enums.Materials;",
    "import gtPlusPlus.core.material.MaterialMisc;",
    "import gtPlusPlus.core.material.MaterialsAlloy;",
    "import gtPlusPlus.core.material.MaterialsElements;",
    "import gtPlusPlus.core.material.nuclear.MaterialsFluorides;",
    "import gtPlusPlus.core.material.nuclear.MaterialsNuclides;",
    "import gtnhlanth.common.register.BotWerkstoffMaterialPool;",
    "import gtnhlanth.common.register.WerkstoffMaterialPool;",
    "",
    "@SuppressWarnings(\"unused\")",
    "public class GTMats {",
    "    private GTMats() { }"
]

detected=set()

for (p, regex) in [*gtStyle, *bwStyle, *gtppStyle]:
    regex = re.compile(regex)

    out.append("")
    out.append("    //#region " + p)

    p2 = None

    if "src/main/java/com/recursive_pineapple/nuclear_horizons" not in p:
        p2 = Path(GT5u, p)
    else:
        p2 = Path(p)

    with open(p2, "r") as file:
        for line in file:
            m = regex.match(line.strip())

            if not m:
                continue

            name = m.groups()[0]

            if name.upper() in detected:
                continue

            detected.add(name.upper())

            if p == "src/main/java/gtPlusPlus/core/material/MaterialsElements.java":
                out.append("    public static final MaterialWrapper " + name.upper() + " = MaterialWrapper.of(() -> MaterialsElements.getInstance()." + name + ");")
            else:
                out.append("    public static final MaterialWrapper " + name.upper() + " = MaterialWrapper.of(() -> " + path.basename(p)[:-5] + "." + name + ");")

    out.append("    //#endregion")

out.append("}")

with open("src/main/java/com/recursive_pineapple/nuclear_horizons/recipes/GTMats.java", "w") as file:
    for line in out:
        file.write(line + "\n")
