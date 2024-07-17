/**
*  Minecraft Clone Math edition : Cybersecurity - A serious game to learn network and cybersecurity
*  Copyright (C) 2024 MeAndTheHomies (Math)
*
*  This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
*
*  This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
*
*  You should have received a copy of the GNU General Public License along with this program. If not, see <https://www.gnu.org/licenses/>.
*/

package fr.math.minecraft.client.network;

public class AuthUser {

    private final String name;
    private final String email;
    private final String skinUrl;
    private final String token;

    public AuthUser(String name, String email, String skinUrl, String token) {
        this.name = name;
        this.email = email;
        this.token = token;
        this.skinUrl = skinUrl;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getSkinUrl() {
        return skinUrl;
    }

    public String getToken() {
        return token;
    }
}
